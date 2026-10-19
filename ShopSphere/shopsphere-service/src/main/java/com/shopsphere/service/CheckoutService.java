package com.shopsphere.service;

import com.shopsphere.common.model.CartItem;
import com.shopsphere.common.model.Coupon;
import com.shopsphere.common.model.Order;
import com.shopsphere.common.model.OrderItem;
import com.shopsphere.common.model.Payment;
import com.shopsphere.dao.AddressDAO;
import com.shopsphere.dao.CartDAO;
import com.shopsphere.dao.ConnectionFactory;
import com.shopsphere.dao.CouponDAO;
import com.shopsphere.dao.OrderDAO;
import com.shopsphere.dao.OrderItemDAO;
import com.shopsphere.dao.PaymentDAO;
import com.shopsphere.dao.ProductDAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CheckoutService {
    private final AddressDAO addressDAO;
    private final CartDAO cartDAO;
    private final CouponDAO couponDAO;
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;
    private final PaymentService paymentService;
    private final CouponService couponService;

    public CheckoutService() { this(new AddressDAO(), new CartDAO(), new CouponDAO(), new OrderDAO(), new OrderItemDAO(), new ProductDAO(), new PaymentService(), new CouponService()); }
    public CheckoutService(AddressDAO addressDAO, CartDAO cartDAO, CouponDAO couponDAO, OrderDAO orderDAO, OrderItemDAO orderItemDAO, ProductDAO productDAO, PaymentService paymentService, CouponService couponService) {
        this.addressDAO = addressDAO; this.cartDAO = cartDAO; this.couponDAO = couponDAO; this.orderDAO = orderDAO; this.orderItemDAO = orderItemDAO; this.productDAO = productDAO; this.paymentService = paymentService; this.couponService = couponService;
    }

    public Order checkout(long userId, long addressId, String couponCode, boolean mockPaymentApproved) throws SQLException {
        if (userId < 1 || addressId < 1) throw new IllegalArgumentException("Choose a valid delivery address.");
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            try {
                addressDAO.findByIdAndUser(connection, addressId, userId).orElseThrow(() -> new IllegalArgumentException("Delivery address does not belong to this account."));
                List<CartItem> items = cartDAO.findByUser(connection, userId);
                if (items.isEmpty()) throw new IllegalArgumentException("Your cart is empty.");
                BigDecimal subtotal = BigDecimal.ZERO;
                for (CartItem item : items) {
                    if (item.getQuantity() < 1 || !item.getProduct().isActive() || item.getProduct().getStock() < item.getQuantity()) throw new IllegalArgumentException("A cart item is unavailable or has insufficient stock.");
                    subtotal = subtotal.add(item.getLineTotal());
                }
                Optional<Coupon> coupon = Optional.empty();
                BigDecimal discount = BigDecimal.ZERO;
                if (couponCode != null && !couponCode.isBlank()) {
                    coupon = couponDAO.findByCode(connection, couponCode.trim().toUpperCase(java.util.Locale.ROOT));
                    Coupon found = coupon.orElseThrow(() -> new IllegalArgumentException("Coupon code is invalid."));
                    if (!couponService.isEligible(found, subtotal)) throw new IllegalArgumentException("Coupon is expired, inactive, or the minimum order is not met.");
                    discount = couponService.calculateDiscount(found, subtotal);
                }
                BigDecimal total = subtotal.subtract(discount).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
                Order order = new Order(); order.setUserId(userId); order.setAddressId(addressId); order.setTotalAmount(total); order.setDiscount(discount); order.setPaymentMethod("MOCK");
                order.setPaymentStatus(mockPaymentApproved ? "SUCCESS" : "FAILED"); order.setOrderStatus(mockPaymentApproved ? "CONFIRMED" : "PAYMENT_FAILED");
                order.setOrderId(orderDAO.create(connection, order));
                for (CartItem cartItem : items) {
                    OrderItem item = new OrderItem(); item.setOrderId(order.getOrderId()); item.setProductId(cartItem.getProductId()); item.setProductName(cartItem.getProduct().getName()); item.setQuantity(cartItem.getQuantity()); item.setPrice(cartItem.getProduct().getSalePrice());
                    orderItemDAO.create(connection, item); order.getItems().add(item);
                }
                Payment payment = paymentService.createMockPayment(connection, order.getOrderId(), total, mockPaymentApproved);
                if (mockPaymentApproved) {
                    for (CartItem item : items) if (!productDAO.decreaseStock(connection, item.getProductId(), item.getQuantity())) throw new IllegalArgumentException("Stock changed during checkout. Please review your cart.");
                    if (coupon.isPresent()) couponDAO.recordUsage(connection, coupon.get().getCouponId(), userId, order.getOrderId());
                    cartDAO.clear(connection, userId);
                }
                connection.commit();
                order.setCreatedAt(LocalDateTime.now());
                return order;
            } catch (SQLException | RuntimeException ex) {
                connection.rollback();
                throw ex;
            }
        }
    }
}

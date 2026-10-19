package com.shopsphere.service;

import com.shopsphere.common.model.Category;
import com.shopsphere.common.model.Coupon;
import com.shopsphere.common.model.Order;
import com.shopsphere.common.model.Product;
import com.shopsphere.common.model.User;
import com.shopsphere.dao.UserDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService {
    private final UserDAO userDAO;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CouponService couponService;
    private final OrderService orderService;
    public AdminService() { this(new UserDAO(), new ProductService(), new CategoryService(), new CouponService(), new OrderService()); }
    public AdminService(UserDAO userDAO, ProductService productService, CategoryService categoryService, CouponService couponService, OrderService orderService) { this.userDAO = userDAO; this.productService = productService; this.categoryService = categoryService; this.couponService = couponService; this.orderService = orderService; }
    public Map<String, Object> dashboard() throws SQLException {
        List<Order> orders = orderService.listAll();
        BigDecimal sales = orders.stream().filter(order -> "SUCCESS".equals(order.getPaymentStatus()) && !"CANCELLED".equals(order.getOrderStatus())).map(Order::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> summary = new HashMap<>(); summary.put("orders", orders.size()); summary.put("customers", userDAO.findAllCustomers().size()); summary.put("sales", sales); summary.put("lowStock", productService.lowStock(5)); summary.put("recentOrders", orders.stream().limit(8).toList());
        return summary;
    }
    public List<User> customers() throws SQLException {
        List<User> customers = userDAO.findAllCustomers();
        customers.forEach(user -> user.setPasswordHash(null));
        return customers;
    }
    public boolean setCustomerActive(long userId, boolean active) throws SQLException { return userDAO.setActive(userId, active); }
    public List<Product> products(String query, Long categoryId, String sort) throws SQLException { return productService.listForAdmin(query, categoryId, sort); }
    public java.util.Optional<Product> findProduct(long id) throws SQLException { return productService.findForAdmin(id); }
    public List<Category> categories() throws SQLException { return categoryService.listAll(); }
    public java.util.Optional<Category> findCategory(long id) throws SQLException { return categoryService.find(id); }
    public List<Coupon> coupons() throws SQLException { return couponService.listAll(); }
    public java.util.Optional<Coupon> findCoupon(long id) throws SQLException { return couponService.find(id); }
    public List<Order> orders() throws SQLException { return orderService.listAll(); }
    public long saveProduct(Product product) throws SQLException { return productService.save(product); }
    public long saveCategory(Category category) throws SQLException { return categoryService.save(category); }
    public long saveCoupon(Coupon coupon) throws SQLException { return couponService.save(coupon); }
    public boolean updateOrder(long orderId, String status) throws SQLException { return orderService.updateStatus(orderId, status); }
    public boolean updateStock(long productId, int stock) throws SQLException { return productService.updateStock(productId, stock); }
    public boolean deactivateProduct(long productId) throws SQLException { return productService.deactivate(productId); }
    public boolean deactivateCategory(long categoryId) throws SQLException { return categoryService.deactivate(categoryId); }
    public boolean setCouponActive(long couponId, boolean active) throws SQLException { return couponService.setActive(couponId, active); }
}

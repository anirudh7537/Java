package com.shopsphere.service;

import com.shopsphere.common.model.Order;
import com.shopsphere.dao.OrderDAO;
import com.shopsphere.dao.OrderItemDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class OrderService {
    private static final Map<String, Set<String>> TRANSITIONS = Map.of(
        "PENDING", Set.of("CONFIRMED", "CANCELLED"),
        "CONFIRMED", Set.of("PROCESSING", "CANCELLED"),
        "PROCESSING", Set.of("SHIPPED", "CANCELLED"),
        "SHIPPED", Set.of("DELIVERED"),
        "DELIVERED", Set.of(),
        "CANCELLED", Set.of(),
        "PAYMENT_FAILED", Set.of()
    );
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    public OrderService() { this(new OrderDAO(), new OrderItemDAO()); }
    public OrderService(OrderDAO orderDAO, OrderItemDAO orderItemDAO) { this.orderDAO = orderDAO; this.orderItemDAO = orderItemDAO; }
    public List<Order> listForUser(long userId) throws SQLException { List<Order> orders = orderDAO.findByUser(userId); orders.forEach(this::attachItems); return orders; }
    public List<Order> listAll() throws SQLException { List<Order> orders = orderDAO.findAll(); orders.forEach(this::attachItems); return orders; }
    public Optional<Order> details(long orderId, long userId) throws SQLException { Optional<Order> order = orderDAO.findByIdAndUser(orderId, userId); order.ifPresent(this::attachItems); return order; }
    public Optional<Order> adminDetails(long orderId) throws SQLException { Optional<Order> order = orderDAO.findById(orderId); order.ifPresent(this::attachItems); return order; }
    public boolean updateStatus(long orderId, String next) throws SQLException {
        String status = next == null ? "" : next.trim().toUpperCase(java.util.Locale.ROOT);
        Order order = orderDAO.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order was not found."));
        if (!TRANSITIONS.getOrDefault(order.getOrderStatus(), Set.of()).contains(status)) throw new IllegalArgumentException("That order status transition is not allowed.");
        return orderDAO.updateStatus(orderId, status);
    }
    private void attachItems(Order order) { try { order.setItems(orderItemDAO.findByOrder(order.getOrderId())); } catch (SQLException ex) { throw new IllegalStateException("Could not load order items.", ex); } }
}

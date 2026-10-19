package com.shopsphere.dao;

import com.shopsphere.common.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {
    public long create(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO orders(user_id,address_id,total_amount,discount,payment_method,payment_status,order_status) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId()); statement.setLong(2, order.getAddressId()); statement.setBigDecimal(3, order.getTotalAmount()); statement.setBigDecimal(4, order.getDiscount()); statement.setString(5, order.getPaymentMethod()); statement.setString(6, order.getPaymentStatus()); statement.setString(7, order.getOrderStatus()); statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); }
        }
        throw new SQLException("Creating order did not return an id.");
    }
    public Optional<Order> findById(long id) throws SQLException { return queryOne("SELECT * FROM orders WHERE order_id=?", id); }
    public Optional<Order> findByIdAndUser(long id, long userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id=? AND user_id=?")) { statement.setLong(1, id); statement.setLong(2, userId); try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); } }
    }
    private Optional<Order> queryOne(String sql, long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) { statement.setLong(1, id); try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); } }
    }
    public List<Order> findByUser(long userId) throws SQLException { return queryList("SELECT * FROM orders WHERE user_id=? ORDER BY created_at DESC", userId); }
    public List<Order> findAll() throws SQLException { List<Order> orders = new ArrayList<>(); try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders ORDER BY created_at DESC"); ResultSet result = statement.executeQuery()) { while (result.next()) orders.add(map(result)); } return orders; }
    private List<Order> queryList(String sql, long userId) throws SQLException { List<Order> orders = new ArrayList<>(); try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) { statement.setLong(1, userId); try (ResultSet result = statement.executeQuery()) { while (result.next()) orders.add(map(result)); } } return orders; }
    public boolean updateStatus(long orderId, String status) throws SQLException { try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE orders SET order_status=? WHERE order_id=?")) { statement.setString(1, status); statement.setLong(2, orderId); return statement.executeUpdate() == 1; } }
    private Order map(ResultSet result) throws SQLException { return new Order(result.getLong("order_id"), result.getLong("user_id"), result.getLong("address_id"), result.getBigDecimal("total_amount"), result.getBigDecimal("discount"), result.getString("payment_method"), result.getString("payment_status"), result.getString("order_status"), result.getTimestamp("created_at").toLocalDateTime()); }
}

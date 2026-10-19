package com.shopsphere.dao;

import com.shopsphere.common.model.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {
    public void create(Connection connection, OrderItem item) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO order_items(order_id,product_id,quantity,price) VALUES(?,?,?,?)")) { statement.setLong(1, item.getOrderId()); statement.setLong(2, item.getProductId()); statement.setInt(3, item.getQuantity()); statement.setBigDecimal(4, item.getPrice()); statement.executeUpdate(); }
    }
    public List<OrderItem> findByOrder(long orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.*,p.name AS product_name FROM order_items oi JOIN products p ON p.product_id=oi.product_id WHERE oi.order_id=? ORDER BY oi.order_item_id";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) { statement.setLong(1, orderId); try (ResultSet result = statement.executeQuery()) { while (result.next()) items.add(new OrderItem(result.getLong("order_item_id"), result.getLong("order_id"), result.getLong("product_id"), result.getString("product_name"), result.getInt("quantity"), result.getBigDecimal("price"))); } }
        return items;
    }
}

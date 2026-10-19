package com.shopsphere.dao;

import com.shopsphere.common.model.CartItem;
import com.shopsphere.common.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    public void add(long userId, long productId, int quantity) throws SQLException {
        String sql = "INSERT INTO cart(user_id,product_id,quantity) VALUES(?,?,?) ON DUPLICATE KEY UPDATE quantity=quantity+VALUES(quantity)";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) { statement.setLong(1, userId); statement.setLong(2, productId); statement.setInt(3, quantity); statement.executeUpdate(); }
    }
    public boolean updateQuantity(long userId, long productId, int quantity) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE cart SET quantity=? WHERE user_id=? AND product_id=?")) { statement.setInt(1, quantity); statement.setLong(2, userId); statement.setLong(3, productId); return statement.executeUpdate() == 1; }
    }
    public boolean remove(long userId, long productId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM cart WHERE user_id=? AND product_id=?")) { statement.setLong(1, userId); statement.setLong(2, productId); return statement.executeUpdate() == 1; }
    }
    public void clear(long userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM cart WHERE user_id=?")) { statement.setLong(1, userId); statement.executeUpdate(); }
    }
    public void clear(Connection connection, long userId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM cart WHERE user_id=?")) { statement.setLong(1, userId); statement.executeUpdate(); }
    }
    public List<CartItem> findByUser(long userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) { return findByUser(connection, userId); }
    }
    public List<CartItem> findByUser(Connection connection, long userId) throws SQLException {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT ci.cart_id,ci.user_id,ci.product_id,ci.quantity,p.category_id,p.name,p.brand,p.description,p.price,p.discount,p.stock,p.image_url,p.status FROM cart ci JOIN products p ON p.product_id=ci.product_id WHERE ci.user_id=? ORDER BY ci.cart_id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()) { while (result.next()) { Product product = new Product(result.getLong("product_id"), result.getLong("category_id"), result.getString("name"), result.getString("brand"), result.getString("description"), result.getBigDecimal("price"), result.getBigDecimal("discount"), result.getInt("stock"), result.getString("image_url"), result.getBoolean("status")); items.add(new CartItem(result.getLong("cart_id"), userId, product.getProductId(), result.getInt("quantity"), product)); } }
        }
        return items;
    }
    public int count(long userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT COALESCE(SUM(quantity),0) FROM cart WHERE user_id=?")) { statement.setLong(1, userId); try (ResultSet result = statement.executeQuery()) { result.next(); return result.getInt(1); } }
    }
}

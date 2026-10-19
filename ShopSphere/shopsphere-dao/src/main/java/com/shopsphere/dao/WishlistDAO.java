package com.shopsphere.dao;

import com.shopsphere.common.model.Product;
import com.shopsphere.common.model.WishlistItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    public void add(long userId, long productId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO wishlist(user_id,product_id) VALUES(?,?) ON DUPLICATE KEY UPDATE created_at=CURRENT_TIMESTAMP")) { statement.setLong(1, userId); statement.setLong(2, productId); statement.executeUpdate(); }
    }
    public boolean remove(long userId, long productId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM wishlist WHERE user_id=? AND product_id=?")) { statement.setLong(1, userId); statement.setLong(2, productId); return statement.executeUpdate() == 1; }
    }
    public boolean contains(long userId, long productId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM wishlist WHERE user_id=? AND product_id=?")) { statement.setLong(1, userId); statement.setLong(2, productId); try (ResultSet result = statement.executeQuery()) { return result.next(); } }
    }
    public List<WishlistItem> findByUser(long userId) throws SQLException {
        List<WishlistItem> items = new ArrayList<>();
        String sql = "SELECT w.wishlist_id,w.user_id,w.product_id,w.created_at,p.category_id,p.name,p.brand,p.description,p.price,p.discount,p.stock,p.image_url,p.status FROM wishlist w JOIN products p ON p.product_id=w.product_id WHERE w.user_id=? ORDER BY w.created_at DESC";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()) { while (result.next()) { Product product = new Product(result.getLong("product_id"), result.getLong("category_id"), result.getString("name"), result.getString("brand"), result.getString("description"), result.getBigDecimal("price"), result.getBigDecimal("discount"), result.getInt("stock"), result.getString("image_url"), result.getBoolean("status")); items.add(new WishlistItem(result.getLong("wishlist_id"), userId, product.getProductId(), product, result.getTimestamp("created_at").toLocalDateTime())); } }
        }
        return items;
    }
}

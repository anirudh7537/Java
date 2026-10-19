package com.shopsphere.dao;

import com.shopsphere.common.model.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    public long create(Review review) throws SQLException {
        String sql = "INSERT INTO reviews(user_id,product_id,rating,review_text) VALUES(?,?,?,?)";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { statement.setLong(1, review.getUserId()); statement.setLong(2, review.getProductId()); statement.setInt(3, review.getRating()); statement.setString(4, review.getReviewText()); statement.executeUpdate(); try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); } }
        throw new SQLException("Creating review did not return an id.");
    }
    public List<Review> findByProduct(long productId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT r.*,u.name AS user_name FROM reviews r JOIN users u ON u.user_id=r.user_id WHERE r.product_id=? ORDER BY r.created_at DESC";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) { statement.setLong(1, productId); try (ResultSet result = statement.executeQuery()) { while (result.next()) reviews.add(new Review(result.getLong("review_id"), result.getLong("user_id"), result.getString("user_name"), result.getLong("product_id"), result.getInt("rating"), result.getString("review_text"), result.getTimestamp("created_at").toLocalDateTime())); } }
        return reviews;
    }
}

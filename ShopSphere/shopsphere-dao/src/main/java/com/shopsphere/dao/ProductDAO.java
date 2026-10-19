package com.shopsphere.dao;

import com.shopsphere.common.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO {
    private static final String SELECT = "SELECT p.*,c.name AS category_name,COALESCE(AVG(r.rating),0) AS average_rating,COUNT(r.review_id) AS review_count FROM products p JOIN categories c ON c.category_id=p.category_id LEFT JOIN reviews r ON r.product_id=p.product_id ";

    public List<Product> search(String query, Long categoryId, String sort, boolean includeInactive) throws SQLException {
        String orderBy = switch (sort == null ? "featured" : sort) {
            case "price-asc" -> "p.price ASC";
            case "price-desc" -> "p.price DESC";
            case "name" -> "p.name ASC";
            case "rating" -> "average_rating DESC, p.name ASC";
            default -> "p.created_at DESC";
        };
        StringBuilder sql = new StringBuilder(SELECT).append("WHERE 1=1 ");
        if (!includeInactive) sql.append("AND p.status=TRUE AND c.status=TRUE ");
        if (query != null && !query.isBlank()) sql.append("AND (p.name LIKE ? OR p.brand LIKE ? OR p.description LIKE ?) ");
        if (categoryId != null && categoryId > 0) sql.append("AND p.category_id=? ");
        sql.append("GROUP BY p.product_id,c.name ORDER BY ").append(orderBy);
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (query != null && !query.isBlank()) { String pattern = "%" + query.trim() + "%"; statement.setString(index++, pattern); statement.setString(index++, pattern); statement.setString(index++, pattern); }
            if (categoryId != null && categoryId > 0) statement.setLong(index, categoryId);
            try (ResultSet result = statement.executeQuery()) { while (result.next()) products.add(map(result)); }
        }
        return products;
    }

    public Optional<Product> findById(long id) throws SQLException { return findById(id, false); }
    public Optional<Product> findAnyById(long id) throws SQLException { return findById(id, true); }
    private Optional<Product> findById(long id, boolean includeInactive) throws SQLException {
        String sql = SELECT + "WHERE p.product_id=? " + (includeInactive ? "" : "AND p.status=TRUE AND c.status=TRUE ") + "GROUP BY p.product_id,c.name";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); }
        }
    }

    public List<Product> findLowStock(int threshold) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = SELECT + "WHERE p.status=TRUE AND p.stock<=? GROUP BY p.product_id,c.name ORDER BY p.stock,p.name";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, threshold);
            try (ResultSet result = statement.executeQuery()) { while (result.next()) products.add(map(result)); }
        }
        return products;
    }

    public long save(Product product) throws SQLException {
        boolean insert = product.getProductId() == 0;
        String sql = insert ? "INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url,status) VALUES(?,?,?,?,?,?,?,?,?)" : "UPDATE products SET category_id=?,name=?,brand=?,description=?,price=?,discount=?,stock=?,image_url=?,status=? WHERE product_id=?";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(statement, product);
            if (!insert) { statement.setLong(10, product.getProductId()); statement.executeUpdate(); return product.getProductId(); }
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); }
        }
        throw new SQLException("Saving product did not return an id.");
    }

    public boolean deactivate(long id) throws SQLException { return setStatus(id, false); }
    public boolean setStatus(long id, boolean active) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE products SET status=? WHERE product_id=?")) { statement.setBoolean(1, active); statement.setLong(2, id); return statement.executeUpdate() == 1; }
    }
    public boolean updateStock(long id, int stock) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE products SET stock=? WHERE product_id=? AND ? >= 0")) { statement.setInt(1, stock); statement.setLong(2, id); statement.setInt(3, stock); return statement.executeUpdate() == 1; }
    }
    public boolean decreaseStock(Connection connection, long id, int quantity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE products SET stock=stock-? WHERE product_id=? AND status=TRUE AND stock>=?")) { statement.setInt(1, quantity); statement.setLong(2, id); statement.setInt(3, quantity); return statement.executeUpdate() == 1; }
    }

    private void bind(PreparedStatement statement, Product product) throws SQLException {
        statement.setLong(1, product.getCategoryId()); statement.setString(2, product.getName()); statement.setString(3, product.getBrand()); statement.setString(4, product.getDescription()); statement.setBigDecimal(5, product.getPrice()); statement.setBigDecimal(6, product.getDiscount()); statement.setInt(7, product.getStock()); statement.setString(8, product.getImageUrl()); statement.setBoolean(9, product.isActive());
    }
    private Product map(ResultSet result) throws SQLException {
        Product product = new Product(result.getLong("product_id"), result.getLong("category_id"), result.getString("name"), result.getString("brand"), result.getString("description"), result.getBigDecimal("price"), result.getBigDecimal("discount"), result.getInt("stock"), result.getString("image_url"), result.getBoolean("status"));
        product.setCategoryName(result.getString("category_name")); product.setAverageRating(result.getBigDecimal("average_rating")); product.setReviewCount(result.getInt("review_count")); return product;
    }
}

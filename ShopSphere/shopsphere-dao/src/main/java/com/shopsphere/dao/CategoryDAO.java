package com.shopsphere.dao;

import com.shopsphere.common.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAO {
    public List<Category> findAll(boolean activeOnly) throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories" + (activeOnly ? " WHERE status=TRUE" : "") + " ORDER BY name";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {
            while (result.next()) categories.add(map(result));
        }
        return categories;
    }

    public Optional<Category> findById(long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE category_id=?")) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); }
        }
    }

    public long save(Category category) throws SQLException {
        boolean insert = category.getCategoryId() == 0;
        String sql = insert ? "INSERT INTO categories(name,description,status) VALUES(?,?,?)" : "UPDATE categories SET name=?,description=?,status=? WHERE category_id=?";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName()); statement.setString(2, category.getDescription()); statement.setBoolean(3, category.isActive());
            if (insert) { statement.executeUpdate(); try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); } }
            else { statement.setLong(4, category.getCategoryId()); statement.executeUpdate(); return category.getCategoryId(); }
        }
        throw new SQLException("Saving category did not return an id.");
    }

    public boolean deactivate(long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE categories SET status=FALSE WHERE category_id=?")) { statement.setLong(1, id); return statement.executeUpdate() == 1; }
    }

    private Category map(ResultSet result) throws SQLException { return new Category(result.getLong("category_id"), result.getString("name"), result.getString("description"), result.getBoolean("status")); }
}

package com.shopsphere.dao;

import com.shopsphere.common.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    public long create(User user) throws SQLException {
        String sql = "INSERT INTO users(name,email,password,mobile,role,status) VALUES(?,?,?,?,?,?)";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName()); statement.setString(2, user.getEmail()); statement.setString(3, user.getPasswordHash()); statement.setString(4, user.getMobile()); statement.setString(5, user.getRole()); statement.setBoolean(6, user.isActive());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); }
        }
        throw new SQLException("Creating user did not return an id.");
    }

    public Optional<User> findByEmail(String email) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); }
        }
    }

    public Optional<User> findById(long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?")) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); }
        }
    }

    public List<User> findAllCustomers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE role='CUSTOMER' ORDER BY created_at DESC"); ResultSet result = statement.executeQuery()) {
            while (result.next()) users.add(map(result));
        }
        return users;
    }

    public boolean updateProfile(User user) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, mobile=? WHERE user_id=?")) {
            statement.setString(1, user.getName()); statement.setString(2, user.getMobile()); statement.setLong(3, user.getUserId()); return statement.executeUpdate() == 1;
        }
    }

    public boolean updatePassword(long userId, String passwordHash) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE users SET password=? WHERE user_id=?")) {
            statement.setString(1, passwordHash); statement.setLong(2, userId); return statement.executeUpdate() == 1;
        }
    }

    public boolean setActive(long userId, boolean active) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE users SET status=? WHERE user_id=? AND role='CUSTOMER'")) {
            statement.setBoolean(1, active); statement.setLong(2, userId); return statement.executeUpdate() == 1;
        }
    }

    private User map(ResultSet result) throws SQLException {
        return new User(result.getLong("user_id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("mobile"), result.getString("role"), result.getBoolean("status"), result.getTimestamp("created_at").toLocalDateTime());
    }
}

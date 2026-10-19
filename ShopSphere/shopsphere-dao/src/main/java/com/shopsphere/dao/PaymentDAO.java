package com.shopsphere.dao;

import com.shopsphere.common.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PaymentDAO {
    public long create(Connection connection, Payment payment) throws SQLException {
        String sql = "INSERT INTO payments(order_id,payment_method,transaction_reference,amount,payment_status) VALUES(?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { statement.setLong(1, payment.getOrderId()); statement.setString(2, payment.getPaymentMethod()); statement.setString(3, payment.getTransactionReference()); statement.setBigDecimal(4, payment.getAmount()); statement.setString(5, payment.getPaymentStatus()); statement.executeUpdate(); try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); } }
        throw new SQLException("Creating payment did not return an id.");
    }
    public Optional<Payment> findByOrder(long orderId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM payments WHERE order_id=? ORDER BY payment_id DESC LIMIT 1")) { statement.setLong(1, orderId); try (ResultSet result = statement.executeQuery()) { if (!result.next()) return Optional.empty(); return Optional.of(new Payment(result.getLong("payment_id"), result.getLong("order_id"), result.getString("payment_method"), result.getString("transaction_reference"), result.getBigDecimal("amount"), result.getString("payment_status"), result.getTimestamp("created_at").toLocalDateTime())); } }
    }
}

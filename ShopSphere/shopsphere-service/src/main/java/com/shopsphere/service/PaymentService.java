package com.shopsphere.service;

import com.shopsphere.common.model.Payment;
import com.shopsphere.dao.PaymentDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class PaymentService {
    private final PaymentDAO paymentDAO;
    public PaymentService() { this(new PaymentDAO()); }
    public PaymentService(PaymentDAO paymentDAO) { this.paymentDAO = paymentDAO; }
    public Payment createMockPayment(Connection connection, long orderId, BigDecimal amount, boolean approved) throws SQLException {
        Payment payment = new Payment(); payment.setOrderId(orderId); payment.setPaymentMethod("MOCK"); payment.setTransactionReference("MOCK-" + UUID.randomUUID()); payment.setAmount(amount); payment.setPaymentStatus(approved ? "SUCCESS" : "FAILED");
        payment.setPaymentId(paymentDAO.create(connection, payment));
        return payment;
    }
}

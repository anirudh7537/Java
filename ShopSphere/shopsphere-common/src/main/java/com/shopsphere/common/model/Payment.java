package com.shopsphere.common.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private long paymentId;
    private long orderId;
    private String paymentMethod = "MOCK";
    private String transactionReference;
    private BigDecimal amount = BigDecimal.ZERO;
    private String paymentStatus;
    private LocalDateTime createdAt;

    public Payment() { }
    public Payment(long paymentId, long orderId, String paymentMethod, String transactionReference, BigDecimal amount, String paymentStatus, LocalDateTime createdAt) { this.paymentId = paymentId; this.orderId = orderId; this.paymentMethod = paymentMethod; this.transactionReference = transactionReference; this.amount = amount; this.paymentStatus = paymentStatus; this.createdAt = createdAt; }
    public long getPaymentId() { return paymentId; }
    public void setPaymentId(long paymentId) { this.paymentId = paymentId; }
    public long getOrderId() { return orderId; }
    public void setOrderId(long orderId) { this.orderId = orderId; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

package com.shopsphere.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private long orderId;
    private long userId;
    private long addressId;
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private String paymentMethod = "MOCK";
    private String paymentStatus = "PENDING";
    private String orderStatus = "PENDING";
    private LocalDateTime createdAt;
    private List<OrderItem> items = new ArrayList<>();

    public Order() { }
    public Order(long orderId, long userId, long addressId, BigDecimal totalAmount, BigDecimal discount, String paymentMethod, String paymentStatus, String orderStatus, LocalDateTime createdAt) {
        this.orderId = orderId; this.userId = userId; this.addressId = addressId; this.totalAmount = totalAmount; this.discount = discount; this.paymentMethod = paymentMethod; this.paymentStatus = paymentStatus; this.orderStatus = orderStatus; this.createdAt = createdAt;
    }
    public long getOrderId() { return orderId; }
    public void setOrderId(long orderId) { this.orderId = orderId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getAddressId() { return addressId; }
    public void setAddressId(long addressId) { this.addressId = addressId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items == null ? new ArrayList<>() : new ArrayList<>(items); }
    @Override public String toString() { return "Order{orderId=" + orderId + ", totalAmount=" + totalAmount + ", orderStatus='" + orderStatus + "'}"; }
}

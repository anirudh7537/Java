package com.shopsphere.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private long orderItemId;
    private long orderId;
    private long productId;
    private String productName;
    private int quantity;
    private BigDecimal price = BigDecimal.ZERO;

    public OrderItem() { }
    public OrderItem(long orderItemId, long orderId, long productId, String productName, int quantity, BigDecimal price) { this.orderItemId = orderItemId; this.orderId = orderId; this.productId = productId; this.productName = productName; this.quantity = quantity; this.price = price; }
    public long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(long orderItemId) { this.orderItemId = orderItemId; }
    public long getOrderId() { return orderId; }
    public void setOrderId(long orderId) { this.orderId = orderId; }
    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getLineTotal() { return price.multiply(BigDecimal.valueOf(quantity)); }
}

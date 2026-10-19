package com.shopsphere.common.model;

import java.math.BigDecimal;

public class CartItem {
    private long cartId;
    private long userId;
    private long productId;
    private int quantity;
    private Product product;

    public CartItem() { }
    public CartItem(long cartId, long userId, long productId, int quantity, Product product) { this.cartId = cartId; this.userId = userId; this.productId = productId; this.quantity = quantity; this.product = product; }
    public long getCartId() { return cartId; }
    public void setCartId(long cartId) { this.cartId = cartId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public BigDecimal getLineTotal() { return product == null ? BigDecimal.ZERO : product.getSalePrice().multiply(BigDecimal.valueOf(quantity)); }
    @Override public String toString() { return "CartItem{productId=" + productId + ", quantity=" + quantity + "}"; }
}

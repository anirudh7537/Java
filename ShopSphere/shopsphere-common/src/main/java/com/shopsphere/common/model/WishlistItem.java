package com.shopsphere.common.model;

import java.time.LocalDateTime;

public class WishlistItem {
    private long wishlistId;
    private long userId;
    private long productId;
    private Product product;
    private LocalDateTime createdAt;

    public WishlistItem() { }
    public WishlistItem(long wishlistId, long userId, long productId, Product product, LocalDateTime createdAt) { this.wishlistId = wishlistId; this.userId = userId; this.productId = productId; this.product = product; this.createdAt = createdAt; }
    public long getWishlistId() { return wishlistId; }
    public void setWishlistId(long wishlistId) { this.wishlistId = wishlistId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

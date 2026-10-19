package com.shopsphere.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private long productId;
    private long categoryId;
    private String categoryName;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private int stock;
    private String imageUrl;
    private boolean active = true;
    private BigDecimal averageRating = BigDecimal.ZERO;
    private int reviewCount;

    public Product() { }
    public Product(long productId, long categoryId, String name, String brand, String description, BigDecimal price, BigDecimal discount, int stock, String imageUrl, boolean active) {
        this.productId = productId; this.categoryId = categoryId; this.name = name; this.brand = brand; this.description = description; this.price = price; this.discount = discount; this.stock = stock; this.imageUrl = imageUrl; this.active = active;
    }
    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }
    public long getCategoryId() { return categoryId; }
    public void setCategoryId(long categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public BigDecimal getAverageRating() { return averageRating; }
    public void setAverageRating(BigDecimal averageRating) { this.averageRating = averageRating; }
    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
    public BigDecimal getSalePrice() { return price.subtract(price.multiply(discount).movePointLeft(2)).setScale(2, java.math.RoundingMode.HALF_UP); }
    @Override public String toString() { return "Product{productId=" + productId + ", name='" + name + "', price=" + price + ", stock=" + stock + "}"; }
}

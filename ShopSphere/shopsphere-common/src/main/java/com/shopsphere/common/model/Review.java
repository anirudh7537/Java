package com.shopsphere.common.model;

import java.time.LocalDateTime;

public class Review {
    private long reviewId;
    private long userId;
    private String userName;
    private long productId;
    private int rating;
    private String reviewText;
    private LocalDateTime createdAt;

    public Review() { }
    public Review(long reviewId, long userId, String userName, long productId, int rating, String reviewText, LocalDateTime createdAt) { this.reviewId = reviewId; this.userId = userId; this.userName = userName; this.productId = productId; this.rating = rating; this.reviewText = reviewText; this.createdAt = createdAt; }
    public long getReviewId() { return reviewId; }
    public void setReviewId(long reviewId) { this.reviewId = reviewId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

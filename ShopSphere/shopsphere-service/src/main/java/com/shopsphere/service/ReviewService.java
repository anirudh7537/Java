package com.shopsphere.service;

import com.shopsphere.common.model.Review;
import com.shopsphere.dao.ProductDAO;
import com.shopsphere.dao.ReviewDAO;
import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    private final ReviewDAO reviewDAO;
    private final ProductDAO productDAO;
    public ReviewService() { this(new ReviewDAO(), new ProductDAO()); }
    public ReviewService(ReviewDAO reviewDAO, ProductDAO productDAO) { this.reviewDAO = reviewDAO; this.productDAO = productDAO; }
    public List<Review> listForProduct(long productId) throws SQLException { return reviewDAO.findByProduct(productId); }
    public long submit(long userId, long productId, int rating, String text) throws SQLException {
        if (userId < 1 || rating < 1 || rating > 5 || text == null || text.isBlank() || text.length() > 2000) throw new IllegalArgumentException("Enter a review and a rating from 1 to 5.");
        if (productDAO.findById(productId).isEmpty()) throw new IllegalArgumentException("Product is unavailable.");
        Review review = new Review(); review.setUserId(userId); review.setProductId(productId); review.setRating(rating); review.setReviewText(text.trim()); return reviewDAO.create(review);
    }
}

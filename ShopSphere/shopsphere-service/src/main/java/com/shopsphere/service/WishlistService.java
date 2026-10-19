package com.shopsphere.service;

import com.shopsphere.common.model.WishlistItem;
import com.shopsphere.dao.ProductDAO;
import com.shopsphere.dao.WishlistDAO;
import java.sql.SQLException;
import java.util.List;

public class WishlistService {
    private final WishlistDAO wishlistDAO;
    private final ProductDAO productDAO;
    public WishlistService() { this(new WishlistDAO(), new ProductDAO()); }
    public WishlistService(WishlistDAO wishlistDAO, ProductDAO productDAO) { this.wishlistDAO = wishlistDAO; this.productDAO = productDAO; }
    public List<WishlistItem> getItems(long userId) throws SQLException { return wishlistDAO.findByUser(userId); }
    public void add(long userId, long productId) throws SQLException {
        if (userId < 1 || productDAO.findById(productId).isEmpty()) throw new IllegalArgumentException("Product is unavailable.");
        wishlistDAO.add(userId, productId);
    }
    public void remove(long userId, long productId) throws SQLException { wishlistDAO.remove(userId, productId); }
    public boolean contains(long userId, long productId) throws SQLException { return wishlistDAO.contains(userId, productId); }
}

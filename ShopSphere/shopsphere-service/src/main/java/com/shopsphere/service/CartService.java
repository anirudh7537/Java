package com.shopsphere.service;

import com.shopsphere.common.model.CartItem;
import com.shopsphere.common.model.Product;
import com.shopsphere.dao.CartDAO;
import com.shopsphere.dao.ProductDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CartService {
    private final CartDAO cartDAO;
    private final ProductDAO productDAO;
    public CartService() { this(new CartDAO(), new ProductDAO()); }
    public CartService(CartDAO cartDAO, ProductDAO productDAO) { this.cartDAO = cartDAO; this.productDAO = productDAO; }
    public List<CartItem> getItems(long userId) throws SQLException { return cartDAO.findByUser(userId); }
    public int getCount(long userId) throws SQLException { return cartDAO.count(userId); }
    public BigDecimal getSubtotal(long userId) throws SQLException { return getItems(userId).stream().map(CartItem::getLineTotal).reduce(BigDecimal.ZERO, BigDecimal::add); }
    public void add(long userId, long productId, int quantity) throws SQLException {
        if (userId < 1 || productId < 1 || quantity < 1) throw new IllegalArgumentException("Choose a valid product and quantity.");
        Product product = productDAO.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product is unavailable."));
        int existing = getItems(userId).stream().filter(item -> item.getProductId() == productId).mapToInt(CartItem::getQuantity).sum();
        if (existing + quantity > product.getStock()) throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        cartDAO.add(userId, productId, quantity);
    }
    public void update(long userId, long productId, int quantity) throws SQLException {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least one.");
        Product product = productDAO.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product is unavailable."));
        if (quantity > product.getStock()) throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        if (!cartDAO.updateQuantity(userId, productId, quantity)) throw new IllegalArgumentException("Cart item was not found.");
    }
    public void remove(long userId, long productId) throws SQLException { cartDAO.remove(userId, productId); }
    public void clear(long userId) throws SQLException { cartDAO.clear(userId); }
}

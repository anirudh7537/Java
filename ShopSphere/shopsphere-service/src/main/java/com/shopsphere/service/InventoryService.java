package com.shopsphere.service;

import com.shopsphere.common.model.Product;
import com.shopsphere.dao.ProductDAO;
import java.sql.SQLException;
import java.util.Optional;

public class InventoryService {
    private final ProductDAO productDAO;
    public InventoryService() { this(new ProductDAO()); }
    public InventoryService(ProductDAO productDAO) { this.productDAO = productDAO; }
    public Optional<Product> getProduct(long productId) throws SQLException { return productDAO.findById(productId); }
    public int getStock(long productId) throws SQLException { return getProduct(productId).map(Product::getStock).orElse(-1); }
    public boolean updateStock(long productId, int quantity) throws SQLException {
        if (productId < 1 || quantity < 0) throw new IllegalArgumentException("Product and non-negative stock are required.");
        if (getProduct(productId).isEmpty()) throw new IllegalArgumentException("Product does not exist.");
        return productDAO.updateStock(productId, quantity);
    }
}

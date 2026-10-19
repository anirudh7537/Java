package com.shopsphere.service;

import com.shopsphere.common.model.Product;
import com.shopsphere.dao.ProductDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductDAO productDAO;
    public ProductService() { this(new ProductDAO()); }
    public ProductService(ProductDAO productDAO) { this.productDAO = productDAO; }
    public List<Product> search(String query, Long categoryId, String sort) throws SQLException { return productDAO.search(query, categoryId, sort, false); }
    public List<Product> listForAdmin(String query, Long categoryId, String sort) throws SQLException { return productDAO.search(query, categoryId, sort, true); }
    public Optional<Product> find(long id) throws SQLException { if (id < 1) return Optional.empty(); return productDAO.findById(id); }
    public Optional<Product> findForAdmin(long id) throws SQLException { if (id < 1) return Optional.empty(); return productDAO.findAnyById(id); }
    public long save(Product product) throws SQLException {
        if (product.getCategoryId() < 1 || product.getName() == null || product.getName().isBlank()) throw new IllegalArgumentException("A category and product name are required.");
        if (product.getPrice() == null || product.getPrice().signum() < 0 || product.getDiscount() == null || product.getDiscount().compareTo(BigDecimal.ZERO) < 0 || product.getDiscount().compareTo(BigDecimal.valueOf(100)) > 0 || product.getStock() < 0) throw new IllegalArgumentException("Price, discount, or stock is invalid.");
        return productDAO.save(product);
    }
    public boolean deactivate(long id) throws SQLException { if (id < 1) return false; return productDAO.deactivate(id); }
    public boolean updateStock(long id, int stock) throws SQLException { if (id < 1 || stock < 0) throw new IllegalArgumentException("Stock cannot be negative."); return productDAO.updateStock(id, stock); }
    public List<Product> lowStock(int threshold) throws SQLException { if (threshold < 0) throw new IllegalArgumentException("Threshold cannot be negative."); return productDAO.findLowStock(threshold); }
}

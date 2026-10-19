package com.shopsphere.service;

import com.shopsphere.common.model.Category;
import com.shopsphere.dao.CategoryDAO;
import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;
    public CategoryService() { this(new CategoryDAO()); }
    public CategoryService(CategoryDAO categoryDAO) { this.categoryDAO = categoryDAO; }
    public List<Category> listActive() throws SQLException { return categoryDAO.findAll(true); }
    public List<Category> listAll() throws SQLException { return categoryDAO.findAll(false); }
    public java.util.Optional<Category> find(long id) throws SQLException { return categoryDAO.findById(id); }
    public long save(Category category) throws SQLException {
        if (category.getName() == null || category.getName().isBlank()) throw new IllegalArgumentException("Category name is required.");
        return categoryDAO.save(category);
    }
    public boolean deactivate(long id) throws SQLException { if (id < 1) return false; return categoryDAO.deactivate(id); }
}

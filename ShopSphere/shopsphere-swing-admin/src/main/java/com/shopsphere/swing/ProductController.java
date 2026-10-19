package com.shopsphere.swing;

import com.shopsphere.common.model.Product;
import com.shopsphere.service.ProductService;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductService products;
    public ProductController() { this(new ProductService()); }
    public ProductController(ProductService products) { this.products = products; }
    public List<Product> search(String query) throws SQLException { return products.listForAdmin(query, null, "name"); }
    public long save(Product product) throws SQLException { return products.save(product); }
    public boolean deactivate(long productId) throws SQLException { return products.deactivate(productId); }
}

package com.shopsphere.network;

import com.shopsphere.common.model.Product;
import com.shopsphere.service.InventoryService;
import com.shopsphere.service.ProductService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class InventoryCommandHandler implements Function<String, Serializable> {
    private final ProductService products;
    private final InventoryService inventory;
    public InventoryCommandHandler(ProductService products, InventoryService inventory) { this.products = products; this.inventory = inventory; }
    @Override public Serializable apply(String request) {
        try {
            String[] parts = request.split("\\|", 2);
            return switch (parts[0]) {
                case "GET_PRODUCT" -> products.find(parseId(parts)).orElse(null);
                case "GET_STOCK" -> inventory.getStock(parseId(parts));
                case "SEARCH_PRODUCT" -> (Serializable) products.search(parts.length == 1 ? "" : parts[1], null, "name");
                default -> "ERROR|Unknown command";
            };
        } catch (IllegalArgumentException ex) { return "ERROR|" + ex.getMessage(); }
        catch (SQLException ex) { return "ERROR|ShopSphere inventory is unavailable"; }
    }
    private long parseId(String[] parts) {
        if (parts.length < 2) throw new IllegalArgumentException("Product id is required");
        try { long id = Long.parseLong(parts[1]); if (id > 0) return id; }
        catch (NumberFormatException ignored) { }
        throw new IllegalArgumentException("Product id must be a positive integer");
    }
}

package com.shopsphere.rmi.client;

import com.shopsphere.rmi.api.InventoryService;
import java.rmi.registry.LocateRegistry;

public final class RmiClientMain {
    private RmiClientMain() { }
    public static void main(String[] args) throws Exception {
        String host = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 1099;
        int productId = args.length > 2 ? Integer.parseInt(args[2]) : 1;
        InventoryService inventory = (InventoryService) LocateRegistry.getRegistry(host, port).lookup("ShopSphereInventory");
        System.out.println("Product: " + inventory.getProduct(productId));
        System.out.println("Stock: " + inventory.getStock(productId));
    }
}

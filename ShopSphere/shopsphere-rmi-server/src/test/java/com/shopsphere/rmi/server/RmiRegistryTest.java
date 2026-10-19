package com.shopsphere.rmi.server;

import com.shopsphere.rmi.api.InventoryService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.shopsphere.common.model.Product;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class RmiRegistryTest {
    @Test
    void registryLookupCallsRemoteInventory() throws Exception {
        int port;
        try (ServerSocket available = new ServerSocket(0)) { port = available.getLocalPort(); }
        Registry registry = LocateRegistry.createRegistry(port);
        InventoryServiceRemote implementation = new InventoryServiceRemote(new com.shopsphere.service.InventoryService() {
            @Override public Optional<Product> getProduct(long productId) { return Optional.of(new Product(productId, 1, "Remote product", "ShopSphere", "", new BigDecimal("12.00"), BigDecimal.ZERO, 7, "", true)); }
            @Override public int getStock(long productId) { return productId == 9 ? 7 : -1; }
            @Override public boolean updateStock(long productId, int quantity) { return productId == 9 && quantity >= 0; }
        });
        try {
            registry.rebind("ShopSphereInventory", implementation);
            InventoryService remote = (InventoryService) LocateRegistry.getRegistry("127.0.0.1", port).lookup("ShopSphereInventory");
            assertEquals("Remote product", remote.getProduct(9).getName());
            assertEquals(7, remote.getStock(9));
            assertTrue(remote.updateStock(9, 4));
        } finally {
            java.rmi.server.UnicastRemoteObject.unexportObject(implementation, true);
            java.rmi.server.UnicastRemoteObject.unexportObject(registry, true);
        }
    }
}

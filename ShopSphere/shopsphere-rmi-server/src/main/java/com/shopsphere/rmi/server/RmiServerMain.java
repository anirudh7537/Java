package com.shopsphere.rmi.server;

import com.shopsphere.rmi.api.InventoryService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CountDownLatch;

public final class RmiServerMain {
    public static final String BINDING_NAME = "ShopSphereInventory";
    private RmiServerMain() { }
    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 1099;
        Registry registry = LocateRegistry.createRegistry(port);
        InventoryService service = new InventoryServiceRemote();
        registry.rebind(BINDING_NAME, service);
        System.out.println("ShopSphere RMI InventoryService bound on port " + port + " as " + BINDING_NAME);
        new CountDownLatch(1).await();
    }
}

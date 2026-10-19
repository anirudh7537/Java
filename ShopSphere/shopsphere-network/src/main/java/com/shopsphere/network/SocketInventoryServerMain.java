package com.shopsphere.network;

import com.shopsphere.service.InventoryService;
import com.shopsphere.service.ProductService;
import java.util.concurrent.CountDownLatch;

public final class SocketInventoryServerMain {
    private SocketInventoryServerMain() { }
    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5050;
        SocketInventoryServer server = new SocketInventoryServer(port, new InventoryCommandHandler(new ProductService(), new InventoryService()));
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { try { server.close(); } catch (Exception ignored) { } }));
        System.out.println("ShopSphere inventory socket server listening on " + server.getPort());
        new CountDownLatch(1).await();
    }
}

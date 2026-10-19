package com.shopsphere.rmi.server;

import com.shopsphere.common.model.Product;
import com.shopsphere.rmi.api.InventoryService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InventoryServiceRemote extends UnicastRemoteObject implements InventoryService {
    private static final long serialVersionUID = 1L;
    private final com.shopsphere.service.InventoryService inventory;

    public InventoryServiceRemote() throws RemoteException { this(new com.shopsphere.service.InventoryService()); }
    public InventoryServiceRemote(com.shopsphere.service.InventoryService inventory) throws RemoteException { super(0); this.inventory = inventory; }
    @Override public Product getProduct(int id) throws RemoteException {
        try { return inventory.getProduct(id).orElse(null); }
        catch (Exception ex) { throw new RemoteException("Unable to retrieve ShopSphere product.", ex); }
    }
    @Override public int getStock(int productId) throws RemoteException {
        try { return inventory.getStock(productId); }
        catch (Exception ex) { throw new RemoteException("Unable to retrieve ShopSphere stock.", ex); }
    }
    @Override public boolean updateStock(int productId, int quantity) throws RemoteException {
        try { return inventory.updateStock(productId, quantity); }
        catch (Exception ex) { throw new RemoteException("Unable to update ShopSphere stock.", ex); }
    }
}

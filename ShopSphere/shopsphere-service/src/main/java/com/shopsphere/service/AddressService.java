package com.shopsphere.service;

import com.shopsphere.common.model.Address;
import com.shopsphere.dao.AddressDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AddressService {
    private final AddressDAO addressDAO;
    public AddressService() { this(new AddressDAO()); }
    public AddressService(AddressDAO addressDAO) { this.addressDAO = addressDAO; }
    public List<Address> list(long userId) throws SQLException { return addressDAO.findByUser(userId); }
    public Optional<Address> find(long addressId, long userId) throws SQLException { return addressDAO.findByIdAndUser(addressId, userId); }
    public long save(long userId, Address address) throws SQLException {
        require(address.getAddressLine(), "Address is required."); require(address.getCity(), "City is required."); require(address.getState(), "State is required."); require(address.getPincode(), "Postal code is required.");
        if (address.getPincode().length() > 20) throw new IllegalArgumentException("Postal code is too long.");
        address.setUserId(userId);
        if (address.getAddressId() > 0 && find(address.getAddressId(), userId).isEmpty()) throw new IllegalArgumentException("Address does not belong to this account.");
        return addressDAO.save(address);
    }
    public boolean delete(long addressId, long userId) throws SQLException { return addressDAO.delete(addressId, userId); }
    private void require(String value, String error) { if (value == null || value.isBlank()) throw new IllegalArgumentException(error); }
}

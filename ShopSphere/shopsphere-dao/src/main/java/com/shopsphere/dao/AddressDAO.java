package com.shopsphere.dao;

import com.shopsphere.common.model.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDAO {
    public List<Address> findByUser(long userId) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM addresses WHERE user_id=? ORDER BY address_id DESC")) {
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()) { while (result.next()) addresses.add(map(result)); }
        }
        return addresses;
    }

    public Optional<Address> findByIdAndUser(long addressId, long userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM addresses WHERE address_id=? AND user_id=?")) {
            statement.setLong(1, addressId); statement.setLong(2, userId);
            try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); }
        }
    }
    public Optional<Address> findByIdAndUser(Connection connection, long addressId, long userId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM addresses WHERE address_id=? AND user_id=? FOR UPDATE")) {
            statement.setLong(1, addressId); statement.setLong(2, userId);
            try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); }
        }
    }

    public long save(Address address) throws SQLException {
        boolean insert = address.getAddressId() == 0;
        String sql = insert ? "INSERT INTO addresses(user_id,address_line,city,state,pincode,address_type) VALUES(?,?,?,?,?,?)" : "UPDATE addresses SET address_line=?,city=?,state=?,pincode=?,address_type=? WHERE address_id=? AND user_id=?";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            if (insert) statement.setLong(index++, address.getUserId());
            statement.setString(index++, address.getAddressLine()); statement.setString(index++, address.getCity()); statement.setString(index++, address.getState()); statement.setString(index++, address.getPincode()); statement.setString(index++, address.getAddressType());
            if (!insert) { statement.setLong(index++, address.getAddressId()); statement.setLong(index, address.getUserId()); statement.executeUpdate(); return address.getAddressId(); }
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); }
        }
        throw new SQLException("Saving address did not return an id.");
    }

    public boolean delete(long id, long userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM addresses WHERE address_id=? AND user_id=?")) { statement.setLong(1, id); statement.setLong(2, userId); return statement.executeUpdate() == 1; }
    }

    private Address map(ResultSet result) throws SQLException { return new Address(result.getLong("address_id"), result.getLong("user_id"), result.getString("address_line"), result.getString("city"), result.getString("state"), result.getString("pincode"), result.getString("address_type")); }
}

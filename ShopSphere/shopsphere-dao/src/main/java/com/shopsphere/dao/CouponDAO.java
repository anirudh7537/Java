

package com.shopsphere.dao;

import com.shopsphere.common.model.Coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CouponDAO {
    public Optional<Coupon> findById(long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupons WHERE coupon_id=?")) { statement.setLong(1, id); try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); } }
    }
    public Optional<Coupon> findByCode(String code) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) { return findByCode(connection, code); }
    }
    public Optional<Coupon> findByCode(Connection connection, String code) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupons WHERE code=? FOR UPDATE")) { statement.setString(1, code); try (ResultSet result = statement.executeQuery()) { return result.next() ? Optional.of(map(result)) : Optional.empty(); } }
    }
    public List<Coupon> findAll() throws SQLException {
        List<Coupon> coupons = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupons ORDER BY created_at DESC"); ResultSet result = statement.executeQuery()) { while (result.next()) coupons.add(map(result)); }
        return coupons;
    }
    public long save(Coupon coupon) throws SQLException {
        boolean insert = coupon.getCouponId() == 0;
        String sql = insert ? "INSERT INTO coupons(code,discount_type,discount_value,minimum_order,maximum_discount,expiry_date,status) VALUES(?,?,?,?,?,?,?)" : "UPDATE coupons SET code=?,discount_type=?,discount_value=?,minimum_order=?,maximum_discount=?,expiry_date=?,status=? WHERE coupon_id=?";
        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, coupon.getCode()); statement.setString(2, coupon.getDiscountType()); statement.setBigDecimal(3, coupon.getDiscountValue()); statement.setBigDecimal(4, coupon.getMinimumOrder());
            if (coupon.getMaximumDiscount() == null) statement.setNull(5, java.sql.Types.DECIMAL); else statement.setBigDecimal(5, coupon.getMaximumDiscount());
            statement.setTimestamp(6, java.sql.Timestamp.valueOf(coupon.getExpiryDate())); statement.setBoolean(7, coupon.isActive());
            if (!insert) { statement.setLong(8, coupon.getCouponId()); statement.executeUpdate(); return coupon.getCouponId(); }
            statement.executeUpdate(); try (ResultSet keys = statement.getGeneratedKeys()) { if (keys.next()) return keys.getLong(1); }
        }
        throw new SQLException("Saving coupon did not return an id.");
    }
    public boolean recordUsage(Connection connection, long couponId, long userId, long orderId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO coupon_usage(coupon_id,user_id,order_id) VALUES(?,?,?)")) { statement.setLong(1, couponId); statement.setLong(2, userId); statement.setLong(3, orderId); return statement.executeUpdate() == 1; }
    }
    public boolean setActive(long id, boolean active) throws SQLException { try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE coupons SET status=? WHERE coupon_id=?")) { statement.setBoolean(1, active); statement.setLong(2, id); return statement.executeUpdate() == 1; } }
    private Coupon map(ResultSet result) throws SQLException {
        java.math.BigDecimal max = result.getBigDecimal("maximum_discount");
        return new Coupon(result.getLong("coupon_id"), result.getString("code"), result.getString("discount_type"), result.getBigDecimal("discount_value"), result.getBigDecimal("minimum_order"), max == null ? null : result.getBigDecimal("maximum_discount"), result.getTimestamp("expiry_date").toLocalDateTime(), result.getBoolean("status"));
    }
}

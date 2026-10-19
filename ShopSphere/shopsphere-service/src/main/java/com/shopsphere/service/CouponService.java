package com.shopsphere.service;

import com.shopsphere.common.model.Coupon;
import com.shopsphere.dao.CouponDAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CouponService {
    private final CouponDAO couponDAO;
    public CouponService() { this(new CouponDAO()); }
    public CouponService(CouponDAO couponDAO) { this.couponDAO = couponDAO; }
    public Optional<Coupon> findValid(String code, BigDecimal subtotal) throws SQLException {
        if (code == null || code.isBlank()) return Optional.empty();
        Optional<Coupon> found = couponDAO.findByCode(code.trim().toUpperCase(Locale.ROOT));
        if (found.isEmpty() || !isEligible(found.get(), subtotal)) return Optional.empty();
        return found;
    }
    public boolean isEligible(Coupon coupon, BigDecimal subtotal) {
        return coupon.isActive() && coupon.getExpiryDate().isAfter(LocalDateTime.now()) && subtotal.compareTo(coupon.getMinimumOrder()) >= 0;
    }
    public BigDecimal calculateDiscount(Coupon coupon, BigDecimal subtotal) {
        if (!isEligible(coupon, subtotal)) throw new IllegalArgumentException("Coupon is invalid, expired, or the minimum order is not met.");
        BigDecimal discount = "PERCENTAGE".equals(coupon.getDiscountType()) ? subtotal.multiply(coupon.getDiscountValue()).movePointLeft(2) : coupon.getDiscountValue();
        if (coupon.getMaximumDiscount() != null) discount = discount.min(coupon.getMaximumDiscount());
        return discount.min(subtotal).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }
    public List<Coupon> listAll() throws SQLException { return couponDAO.findAll(); }
    public Optional<Coupon> find(long id) throws SQLException { return couponDAO.findById(id); }
    public long save(Coupon coupon) throws SQLException {
        if (coupon.getCode() == null || coupon.getCode().isBlank() || coupon.getExpiryDate() == null || coupon.getDiscountValue() == null || coupon.getDiscountValue().signum() <= 0) throw new IllegalArgumentException("Coupon code, expiry, and positive discount are required.");
        coupon.setCode(coupon.getCode().trim().toUpperCase(Locale.ROOT));
        if (!"FIXED".equals(coupon.getDiscountType()) && !"PERCENTAGE".equals(coupon.getDiscountType())) throw new IllegalArgumentException("Discount type must be FIXED or PERCENTAGE.");
        if ("PERCENTAGE".equals(coupon.getDiscountType()) && coupon.getDiscountValue().compareTo(BigDecimal.valueOf(100)) > 0) throw new IllegalArgumentException("Percentage discount cannot exceed 100.");
        return couponDAO.save(coupon);
    }
    public boolean setActive(long id, boolean active) throws SQLException { return couponDAO.setActive(id, active); }
}

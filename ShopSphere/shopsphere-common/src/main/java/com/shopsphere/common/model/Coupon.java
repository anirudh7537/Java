package com.shopsphere.common.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Coupon {
    private long couponId;
    private String code;
    private String discountType;
    private BigDecimal discountValue = BigDecimal.ZERO;
    private BigDecimal minimumOrder = BigDecimal.ZERO;
    private BigDecimal maximumDiscount;
    private LocalDateTime expiryDate;
    private boolean active = true;

    public Coupon() { }
    public Coupon(long couponId, String code, String discountType, BigDecimal discountValue, BigDecimal minimumOrder, BigDecimal maximumDiscount, LocalDateTime expiryDate, boolean active) { this.couponId = couponId; this.code = code; this.discountType = discountType; this.discountValue = discountValue; this.minimumOrder = minimumOrder; this.maximumDiscount = maximumDiscount; this.expiryDate = expiryDate; this.active = active; }
    public long getCouponId() { return couponId; }
    public void setCouponId(long couponId) { this.couponId = couponId; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    public BigDecimal getMinimumOrder() { return minimumOrder; }
    public void setMinimumOrder(BigDecimal minimumOrder) { this.minimumOrder = minimumOrder; }
    public BigDecimal getMaximumDiscount() { return maximumDiscount; }
    public void setMaximumDiscount(BigDecimal maximumDiscount) { this.maximumDiscount = maximumDiscount; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

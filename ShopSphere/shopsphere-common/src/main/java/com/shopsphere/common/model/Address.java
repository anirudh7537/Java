package com.shopsphere.common.model;

public class Address {
    private long addressId;
    private long userId;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
    private String addressType = "HOME";

    public Address() { }
    public Address(long addressId, long userId, String addressLine, String city, String state, String pincode, String addressType) {
        this.addressId = addressId; this.userId = userId; this.addressLine = addressLine; this.city = city; this.state = state; this.pincode = pincode; this.addressType = addressType;
    }
    public long getAddressId() { return addressId; }
    public void setAddressId(long addressId) { this.addressId = addressId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public String getAddressType() { return addressType; }
    public void setAddressType(String addressType) { this.addressType = addressType; }
    public String getFormattedAddress() { return String.join(", ", addressLine, city, state, pincode); }
    @Override public String toString() { return getFormattedAddress(); }
}

package com.shopsphere.common.model;

import java.time.LocalDateTime;

public class User {
    private long userId;
    private String name;
    private String email;
    private String passwordHash;
    private String mobile;
    private String role = "CUSTOMER";
    private boolean active = true;
    private LocalDateTime createdAt;

    public User() { }
    public User(long userId, String name, String email, String passwordHash, String mobile, String role, boolean active, LocalDateTime createdAt) {
        this.userId = userId; this.name = name; this.email = email; this.passwordHash = passwordHash; this.mobile = mobile; this.role = role; this.active = active; this.createdAt = createdAt;
    }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    @Override public String toString() { return "User{userId=" + userId + ", name='" + name + "', email='" + email + "', role='" + role + "'}"; }
}

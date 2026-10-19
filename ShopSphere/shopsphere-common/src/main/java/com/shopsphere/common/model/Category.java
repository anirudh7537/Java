package com.shopsphere.common.model;

public class Category {
    private long categoryId;
    private String name;
    private String description;
    private boolean active = true;

    public Category() { }
    public Category(long categoryId, String name, String description, boolean active) { this.categoryId = categoryId; this.name = name; this.description = description; this.active = active; }
    public long getCategoryId() { return categoryId; }
    public void setCategoryId(long categoryId) { this.categoryId = categoryId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    @Override public String toString() { return "Category{categoryId=" + categoryId + ", name='" + name + "'}"; }
}

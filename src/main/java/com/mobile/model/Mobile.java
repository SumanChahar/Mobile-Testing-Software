package com.mobile.model;

public class Mobile {
    private String id;
    private String name;
    private String detail;
    private Boolean isAvailable;
    private Integer quantity;

    public Mobile(String id, String name, String detail, Boolean isAvailable, Integer quantity) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

package com.mobile.dto;

import java.util.List;

public class MobileDTO {
    private String id;
    private String name;
    private String detail;
    private Boolean isAvailable;
    private Integer quantity;
    private List<BookingDetailDTO> bookings;

    public MobileDTO(String id, String name, String detail, Boolean isAvailable, Integer quantity) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
    }

    public List<BookingDetailDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDetailDTO> bookings) {
        this.bookings = bookings;
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

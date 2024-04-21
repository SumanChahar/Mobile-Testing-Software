package com.mobile.model;

public class Booking {
    private String id;
    private String mobileId;
    private String userId;
    private String bookingDate;
    private String returnDate;

    public Booking(String id, String mobileId, String userId, String bookingDate, String returnDate) {
        this.id = id;
        this.mobileId = mobileId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
    }

    public String getId() {
        return id;
    }

    public String getMobileId() {
        return mobileId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}

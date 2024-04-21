package com.mobile.dto;

public class BookingDetailDTO {
    private String userName;
    private String bookingDate;
    private String returnDate;
    private String phoneNumber;

    public BookingDetailDTO(String userName, String bookingDate, String returnDate, String phoneNumber) {
        this.userName = userName;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}

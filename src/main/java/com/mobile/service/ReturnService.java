package com.mobile.service;

import com.mobile.exception.BookingNotFoundException;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.model.Booking;
import com.mobile.model.Mobile;

public class ReturnService {
    private final BookingService bookingService;
    private final MobileService mobileService;

    public ReturnService(BookingService bookingService, MobileService mobileService) {
        this.bookingService = bookingService;
        this.mobileService = mobileService;
    }

    public void returnPhone(String phoneName, String bookingId) {
        try {
            Booking booking = bookingService.getBookingById(bookingId);
            Mobile mobile = mobileService.getMobile(phoneName);
            int quantity = mobile.getQuantity() + 1;
            mobile.setAvailable(true);
            mobile.setQuantity(quantity);
            bookingService.removeBookingById(booking.getId());
            System.out.println("Returned " + phoneName);
            System.out.println("Removed the booking with Id : " + bookingId);
        } catch (BookingNotFoundException | MobileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

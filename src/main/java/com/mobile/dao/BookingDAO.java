package com.mobile.dao;

import com.mobile.database.BookingDB;
import com.mobile.exception.BookingNotFoundException;
import com.mobile.model.Booking;

import java.util.List;

public class BookingDAO {
    private final BookingDB bookingDB;

    public BookingDAO() {
        this.bookingDB = new BookingDB();
    }


    public void createBooking(Booking booking) {
        bookingDB.createBooking(booking);
    }

    public List<Booking> getBookingByPhoneId(String phoneId) {
        return bookingDB.getBookingByPhoneId(phoneId);
    }

    public Booking getBookingById(String id) {
        Booking booking = bookingDB.getBookingById(id);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with id : " + id + " not found");
        }
        return booking;
    }

    public void removeBooking(String id) {
        bookingDB.deleteBooking(id);
    }
}

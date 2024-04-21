package com.mobile.service;

import com.mobile.dao.BookingDAO;
import com.mobile.exception.BookingNotFoundException;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.exception.UserNotFoundException;
import com.mobile.model.Booking;
import com.mobile.model.Mobile;
import com.mobile.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingService {
    private final BookingDAO bookingDAO;
    private final UserService userService;
    private MobileService mobileService;

    public BookingService(UserService userService) {
        this.bookingDAO = new BookingDAO();
        this.userService = userService;
    }

    public BookingService(BookingDAO bookingDAO, UserService userService) {
        this.bookingDAO = bookingDAO;
        this.userService = userService;
    }

    public void setMobileService(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    public List<Booking> getBookingByPhoneId(String phoneId) {
        return bookingDAO.getBookingByPhoneId(phoneId);
    }

    public void createBooking(String userName, String phoneName, int returnDays) {
        try {
            User user = userService.getUserById(userName);
            Mobile mobile = mobileService.getMobile(phoneName);
            if (!mobile.getAvailable()) {
                System.out.println(phoneName + " is not available");
                return;
            }
            String bookingDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
            String returnDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now().plusDays(returnDays));
            String id = userName + "_" + phoneName + "_" + bookingDate;
            Booking booking = new Booking(id, mobile.getId(), user.getId(), bookingDate, returnDate);
            bookingDAO.createBooking(booking);
            int quantity = mobile.getQuantity() - 1;
            if (quantity == 0) {
                mobile.setAvailable(false);
            }
            mobile.setQuantity(quantity);
            mobileService.setMobileInDB(mobile);
            System.out.println("Booking was successful with id : " + id);
        } catch (UserNotFoundException | MobileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Booking getBookingById(String id) throws BookingNotFoundException {
        return bookingDAO.getBookingById(id);
    }

    public void removeBookingById(String id) {
        bookingDAO.removeBooking(id);
    }
}

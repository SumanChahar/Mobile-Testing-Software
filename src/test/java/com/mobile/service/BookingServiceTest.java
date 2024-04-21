package com.mobile.service;

import com.mobile.dao.BookingDAO;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.exception.UserNotFoundException;
import com.mobile.model.Booking;
import com.mobile.model.Mobile;
import com.mobile.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class BookingServiceTest {
    private MobileService mobileService;
    private UserService userService;
    private BookingDAO bookingDAO;
    private BookingService bookingService;
    private Booking booking;
    private Mobile mobile;
    private User user;

    @BeforeEach
    public void setUp() {
        mobileService = mock(MobileService.class);
        userService = mock(UserService.class);
        bookingDAO = mock(BookingDAO.class);
        bookingService = new BookingService(bookingDAO, userService);
        bookingService.setMobileService(mobileService);

        booking = new Booking("id", "m1", "u1",
                "21-04-2024", "22-04-2024");
        mobile = new Mobile("m1", "Iphone 12", "Iphone", true, 1);
        user = new User("abc", "John", "1234567890", "England");
    }

    @Test
    void getBookingByPhoneId() {
        List<Booking> bookings = List.of(booking);
        Mockito.when(bookingDAO.getBookingByPhoneId(Mockito.anyString())).thenReturn(bookings);
        List<Booking> gotBookings = bookingService.getBookingByPhoneId("Iphone 13");
        assertEquals(bookings, gotBookings);
    }

    @Test
    void getBookingById() {
        Mockito.when(bookingDAO.getBookingById(Mockito.anyString())).thenReturn(booking);
        Booking gotBooking = bookingService.getBookingById("id");
        assertEquals(booking.getBookingDate(), gotBooking.getBookingDate());
        assertEquals(booking.getId(), gotBooking.getId());
        assertEquals(booking.getReturnDate(), gotBooking.getReturnDate());
        assertEquals(booking.getMobileId(), gotBooking.getMobileId());
        assertEquals(booking.getUserId(), gotBooking.getUserId());
    }

    @Test
    void removeBookingById() {
        Mockito.doNothing().when(bookingDAO).removeBooking("id");
        bookingService.removeBookingById("id");
    }

    @Test
    void createBooking() {
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenReturn(mobile);
        Mockito.doNothing().when(bookingDAO).createBooking(booking);
        Mockito.doNothing().when(mobileService).setMobileInDB(mobile);
        bookingService.createBooking("abc", "Iphone 13", 1);
    }

    @Test
    void createBookingWithMoreThanOneQuantity() {
        mobile.setQuantity(2);
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenReturn(mobile);
        Mockito.doNothing().when(bookingDAO).createBooking(booking);
        Mockito.doNothing().when(mobileService).setMobileInDB(mobile);
        bookingService.createBooking("abc", "Iphone 13", 1);
    }

    @Test
    void createBookingWithNotAvailable() {
        mobile.setAvailable(false);
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenReturn(mobile);
        Mockito.doNothing().when(bookingDAO).createBooking(booking);
        Mockito.doNothing().when(mobileService).setMobileInDB(mobile);
        bookingService.createBooking("abc", "Iphone 13", 1);
    }

    @Test
    void createBookingWithUserNotFound() {
        Mockito.when(userService.getUserById(Mockito.anyString())).thenThrow(UserNotFoundException.class);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenReturn(mobile);
        Mockito.doNothing().when(bookingDAO).createBooking(booking);
        Mockito.doNothing().when(mobileService).setMobileInDB(mobile);
        bookingService.createBooking("abc", "Iphone 13", 1);
    }

    @Test
    void createBookingWithMobileNotFound() {
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenThrow(MobileNotFoundException.class);
        Mockito.doNothing().when(bookingDAO).createBooking(booking);
        Mockito.doNothing().when(mobileService).setMobileInDB(mobile);
        bookingService.createBooking("abc", "Iphone 13", 1);
    }
}
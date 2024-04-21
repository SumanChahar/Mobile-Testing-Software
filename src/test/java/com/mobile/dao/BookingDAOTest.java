package com.mobile.dao;

import com.mobile.database.BookingDB;
import com.mobile.exception.BookingNotFoundException;
import com.mobile.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookingDAOTest {

    private BookingDAO bookingDAO;
    private BookingDB bookingDBMock;
    private Booking booking;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Create a mock BookingDB object
        bookingDBMock = mock(BookingDB.class);

        // Instantiate the BookingDAO
        bookingDAO = new BookingDAO();
        // Then you can set the BookingDB instance using a setter method or reflection
        // Use reflection to inject the mock BookingDB
        Field field = BookingDAO.class.getDeclaredField("bookingDB");
        field.setAccessible(true);
        field.set(bookingDAO, bookingDBMock);

        booking = new Booking("id", "m1", "u1",
                "21-04-2024", "22-04-2024");
    }

    @Test
    public void testCreateBooking() {
        // Call the method to be tested
        bookingDAO.createBooking(booking);

        // Verify that the createBooking method of the mock BookingDB was called with the correct argument
        verify(bookingDBMock).createBooking(booking);
    }

    @Test
    public void testGetBookingByPhoneId() {
        // Mock data
        String phoneId = "m1";
        List<Booking> mockBookings = new ArrayList<>();
        mockBookings.add(booking);
        // Add some mock bookings to the list

        // Configure the mock behavior
        when(bookingDBMock.getBookingByPhoneId(phoneId)).thenReturn(mockBookings);

        // Call the method to be tested
        List<Booking> result = bookingDAO.getBookingByPhoneId(phoneId);

        // Verify that the result matches the expected mock bookings
        assertEquals(mockBookings, result);
    }

    @Test
    public void testGetBookingById_NotFound() {
        // Mock data
        String id = "123";

        // Configure the mock behavior to return null, simulating a booking not found scenario
        when(bookingDBMock.getBookingById(id)).thenReturn(null);

        // Call the method to be tested, expecting an exception

        assertThrows(BookingNotFoundException.class,()-> bookingDAO.getBookingById(id) );

    }

    @Test
    public void testGetBookingById_Found() {
        // Mock data
        String id = "id";

        // Configure the mock behavior
        when(bookingDBMock.getBookingById(id)).thenReturn(booking);

        // Call the method to be tested
        Booking actualBooking = bookingDAO.getBookingById(id);

        // Verify that the result matches the expected mock booking
        assertEquals(booking, actualBooking);
    }
    @Test
    public void testRemoveBooking() {
        String id = "someId";
        bookingDAO.removeBooking(id);
        verify(bookingDBMock).deleteBooking(id);
    }
}
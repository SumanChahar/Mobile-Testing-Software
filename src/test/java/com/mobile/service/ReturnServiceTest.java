package com.mobile.service;

import com.mobile.exception.BookingNotFoundException;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.model.Booking;
import com.mobile.model.Mobile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class ReturnServiceTest {
    private BookingService bookingService;
    private MobileService mobileService;
    private ReturnService returnService;

    @BeforeEach
    public void setUp() {
        bookingService = mock(BookingService.class);
        mobileService = mock(MobileService.class);
        returnService = new ReturnService(bookingService, mobileService);
    }

    @Test
    void returnPhone() {
        Booking booking = new Booking("id", "m1", "u1",
                "21-04-2024", "22-04-2024");
        Mobile mobile = new Mobile("m1", "Iphone 12", "Iphone", false, 0);

        Mockito.when(bookingService.getBookingById(Mockito.anyString())).thenReturn(booking);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenReturn(mobile);
        Mockito.doNothing().when(bookingService).removeBookingById("id");
        returnService.returnPhone("Iphone 12", "id");
    }

    @Test
    void returnPhoneWithError() {
        Mobile mobile = new Mobile("m1", "Iphone 12", "Iphone", false, 0);
        Mockito.when(bookingService.getBookingById(Mockito.anyString())).thenThrow(BookingNotFoundException.class);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenReturn(mobile);
        Mockito.doNothing().when(bookingService).removeBookingById("id");
        returnService.returnPhone("Iphone 12", "id");
    }

    @Test
    void returnPhoneWithNotFound() {
        Booking booking = new Booking("id", "m1", "u1",
                "21-04-2024", "22-04-2024");
        Mockito.when(bookingService.getBookingById(Mockito.anyString())).thenReturn(booking);
        Mockito.when(mobileService.getMobile(Mockito.anyString())).thenThrow(MobileNotFoundException.class);
        Mockito.doNothing().when(bookingService).removeBookingById("id");
        returnService.returnPhone("Iphone 12", "id");
    }
}
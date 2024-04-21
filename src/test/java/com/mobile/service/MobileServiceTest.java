package com.mobile.service;

import com.mobile.dao.MobileDAO;
import com.mobile.dto.BookingDetailDTO;
import com.mobile.dto.MobileDTO;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.model.Booking;
import com.mobile.model.Mobile;
import com.mobile.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MobileServiceTest {
    private MobileDAO mobileDAO;
    private UserService userService;
    private BookingService bookingService;
    private MobileService mobileService;

    @BeforeEach
    public void setUp() {
        bookingService = mock(BookingService.class);
        userService = mock(UserService.class);
        mobileDAO = mock(MobileDAO.class);
        mobileService = new MobileService(mobileDAO, userService);
        mobileService.setBookingService(bookingService);
    }

    @Test
    void getMobileName() {
        Booking booking = new Booking("id", "m1", "u1",
                "21-04-2024", "22-04-2024");
        Mobile mobile = new Mobile("m1", "Iphone 12", "Iphone", false, 0);
        User user = new User("abc", "John", "1234567890", "England");
        MobileDTO expectedDTO = new MobileDTO(mobile.getId(), mobile.getName(), mobile.getDetail(),
                mobile.getAvailable(), mobile.getQuantity());
        BookingDetailDTO bookingDetailDTO = new BookingDetailDTO(user.getName(), booking.getBookingDate(),
                booking.getReturnDate(), user.getPhone());
        expectedDTO.setBookings(List.of(bookingDetailDTO));

        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user);
        Mockito.when(bookingService.getBookingByPhoneId(Mockito.anyString())).thenReturn(List.of(booking));
        Mockito.when(mobileDAO.getMobileByName(Mockito.anyString())).thenReturn(mobile);

        MobileDTO gotMobileDTO = mobileService.getMobileName("Iphone 12");
        assertEquals(expectedDTO.getName(), gotMobileDTO.getName());
        assertEquals(expectedDTO.getAvailable(), gotMobileDTO.getAvailable());
        assertEquals(expectedDTO.getId(), gotMobileDTO.getId());
        assertEquals(expectedDTO.getDetail(), gotMobileDTO.getDetail());
        assertEquals(expectedDTO.getQuantity(), gotMobileDTO.getQuantity());
        assertEquals(expectedDTO.getBookings().size(), gotMobileDTO.getBookings().size());
    }

    @Test
    void getMobileNameNotFound() {
        Booking booking = new Booking("id", "m1", "u1",
                "21-04-2024", "22-04-2024");
        User user = new User("abc", "John", "1234567890", "England");

        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user);
        Mockito.when(bookingService.getBookingByPhoneId(Mockito.anyString())).thenReturn(List.of(booking));
        Mockito.when(mobileDAO.getMobileByName(Mockito.anyString())).thenThrow(MobileNotFoundException.class);

        MobileDTO gotMobileDTO = mobileService.getMobileName("Iphone 12");
        assertNull(gotMobileDTO);
    }

    @Test
    void getMobile() {
        Mobile mobile = new Mobile("m1", "Iphone 12", "Iphone", false, 0);
        Mockito.when(mobileDAO.getMobileByName(Mockito.anyString())).thenReturn(mobile);
        Mobile gotMobile = mobileService.getMobile("Iphone 12");
        assertEquals(mobile, gotMobile);
    }

    @Test
    void setMobileInDB() {
        Mobile mobile = new Mobile("m1", "Iphone 12", "Iphone", false, 0);
        Mockito.doNothing().when(mobileDAO).setMobileInDB(mobile);
        mobileService.setMobileInDB(mobile);
    }

    static Stream<Arguments> getMobiles() {
        return Stream.of(
                Arguments.arguments(new ArrayList<>()),
                Arguments.arguments(List.of(new Mobile("m1", "Iphone 12", "Iphone", false, 0)))
        );
    }

    @ParameterizedTest
    @MethodSource("getMobiles")
    void getAvailableMobiles(List<Mobile> mobiles) {
        Mockito.when(mobileDAO.getAllAvailableMobiles()).thenReturn(mobiles);
        List<Mobile> gotMobiles = mobileService.getAvailableMobiles();
        assertEquals(mobiles.size(), gotMobiles.size());
    }
}
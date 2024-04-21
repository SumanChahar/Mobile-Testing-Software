package com.mobile.service;

import com.mobile.dao.MobileDAO;
import com.mobile.dto.BookingDetailDTO;
import com.mobile.dto.MobileDTO;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.model.Booking;
import com.mobile.model.Mobile;
import com.mobile.model.User;

import java.util.List;

public class MobileService {
    private final MobileDAO mobileDAO;
    private final UserService userService;
    private BookingService bookingService;

    public MobileService(UserService userService) {
        this.mobileDAO = new MobileDAO();
        this.userService = userService;
    }

    // For Unit Testing
    public MobileService(MobileDAO mobileDAO, UserService userService) {
        this.mobileDAO = mobileDAO;
        this.userService = userService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public MobileDTO getMobileName(String name) {
        try {
            Mobile mobile = mobileDAO.getMobileByName(name);
            MobileDTO mobileDTO = new MobileDTO(mobile.getId(), mobile.getName(), mobile.getDetail(),
                    mobile.getAvailable(), mobile.getQuantity());
            List<Booking> bookingDetailList = bookingService.getBookingByPhoneId(mobile.getId());
            if (!bookingDetailList.isEmpty()) {
                List<BookingDetailDTO> bookings = bookingDetailList.stream()
                        .map(booking -> {
                            User user = userService.getUserById(booking.getUserId());
                            return new BookingDetailDTO(user.getName(), booking.getBookingDate(), booking.getReturnDate(), user.getPhone());
                        }).toList();
                mobileDTO.setBookings(bookings);
            }
            return mobileDTO;
        } catch (MobileNotFoundException mobileNotFoundException) {
            System.out.println(mobileNotFoundException.getMessage());
            return null;
        }
    }

    public Mobile getMobile(String name) throws MobileNotFoundException {
        return mobileDAO.getMobileByName(name);
    }

    public void setMobileInDB(Mobile mobile) {
        mobileDAO.setMobileInDB(mobile);
    }

    public List<Mobile> getAvailableMobiles() {
        List<Mobile> mobiles = mobileDAO.getAllAvailableMobiles();
        if (mobiles.isEmpty()) {
            System.out.println("No mobiles available");
        }
        return mobiles;
    }
}

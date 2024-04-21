package com.mobile.util;

import com.mobile.dto.MobileDTO;
import com.mobile.model.Mobile;

import java.util.List;

/**
 * Utility class for printing mobile details and available mobiles.
 */
public class PrinterUtil {
    /**
     * Prints the details of a given mobile DTO.
     * @param mobileDTO The mobile DTO containing the details to be printed.
     */
    public void printMobileDetails(MobileDTO mobileDTO) {
        if (mobileDTO == null) {
            return;
        }
        System.out.println("--- Mobile details are ---");
        System.out.println("Name : " + mobileDTO.getName());
        System.out.println("Available : " + mobileDTO.getAvailable());
        System.out.println("Details : " + mobileDTO.getDetail());
        System.out.println("Quantity : " + mobileDTO.getQuantity());
        if (mobileDTO.getBookings() != null) {
            System.out.println("--- Bookings ---");
            mobileDTO.getBookings().forEach(bookingDetailDTO -> {
                System.out.println("Booked by : " + bookingDetailDTO.getUserName());
                System.out.println("Booked user contact : " + bookingDetailDTO.getPhoneNumber());
                System.out.println("Booking date : " + bookingDetailDTO.getBookingDate());
                System.out.println("Return date : " + bookingDetailDTO.getReturnDate());
                System.out.println("-----");
            });
        }
    }

    /**
     * Prints the details of available mobiles.
     * @param mobiles The list of available mobiles to be printed.
     */
    public void printAvailableMobiles(List<Mobile> mobiles) {
        System.out.println("=== Available Mobiles ===");
        if (mobiles.isEmpty()) {
            return;
        }
        mobiles.forEach(mobile -> {
            System.out.println("-*-*-*-");
            System.out.println("Mobile Name : " + mobile.getName());
            System.out.println("Mobile Details : " + mobile.getDetail());
            System.out.println("Quantity : " + mobile.getQuantity());
        });
    }
}

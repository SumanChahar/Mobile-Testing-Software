package com.mobile.util;

import com.mobile.dto.MobileDTO;
import com.mobile.model.Mobile;
import com.mobile.service.BookingService;
import com.mobile.service.MobileService;
import com.mobile.service.ReturnService;
import com.mobile.service.UserService;

import java.util.List;
import java.util.Scanner;

/**
 * Main class which takes input from user and perform actions based on user choice
 */
public class Driver {
    private final UserService userService;
    private final MobileService mobileService;
    private final BookingService bookingService;
    private final ReturnService returnService;
    private final PrinterUtil printerUtil;

    // Constructor initializes necessary services and utilities
    public Driver() {
        this.userService = new UserService();
        this.mobileService = new MobileService(userService);
        this.bookingService = new BookingService(userService);
        this.returnService = new ReturnService(bookingService, mobileService);
        mobileService.setBookingService(bookingService);
        bookingService.setMobileService(mobileService);
        this.printerUtil = new PrinterUtil();
    }
    // Main method to drive the application
    public void drive() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int choice = 0;
        // Loop to keep the application running until user exits
        while (true) {
            System.out.println("=== MOBILE SOFTWARE TESTING ===");
            System.out.println("1. View available phones");
            System.out.println("2. View Phone by name");
            System.out.println("3. Book a phone");
            System.out.println("4. Return a phone");
            System.out.println("5. Exit");
            System.out.println("===============================");

            boolean intValid = false;
            while(!intValid) {
                System.out.println("Please enter a choice (1 - 5) :");
                String input = scanner.next().trim();

                if (isInteger(input)) {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 5) {
                        intValid = true;
                    }
                }
            }
            // Perform actions based on user choice
            switch (choice) {
                case 1:
                    List<Mobile> mobiles = mobileService.getAvailableMobiles();
                    printerUtil.printAvailableMobiles(mobiles);
                    break;
                case 2:
                    System.out.print("Enter phone name : ");
                    String phoneName = scanner.next().trim();
                    MobileDTO mobileDTO = mobileService.getMobileName(phoneName);
                    printerUtil.printMobileDetails(mobileDTO);
                    break;
                case 3:
                    // Booking a phone
                    System.out.println("Are you new User (Y/N) : ");
                    String newUser = scanner.next().trim();
                    String userName;
                    if (newUser.equalsIgnoreCase("Y") || newUser.equalsIgnoreCase("Yes")) {
                        System.out.print("Enter Username : ");
                        userName = scanner.next().trim();
                        System.out.print("Enter your name : ");
                        String name = scanner.next().trim();
                        System.out.print("Enter phone : ");
                        String phone = scanner.next().trim();
                        System.out.print("Enter address : ");
                        String address = scanner.next().trim();
                        userService.createUser(userName, name, phone, address);
                        System.out.println("Created user with username : " + userName);
                    }else if (newUser.equalsIgnoreCase("N") || newUser.equals("NO")) {
                        System.out.print("Enter user name : ");
                        userName = scanner.next().trim();
                    } else {
                        System.out.println("Invalid input");
                        continue;
                    }
                    // Booking a phone for the user
                    System.out.print("Enter phone name you want to book: ");
                    String mobile = scanner.next().trim();
                    System.out.print("Enter number of days for phone to be returned : ");
                    int returnDays = Integer.parseInt(scanner.next().trim());
                    bookingService.createBooking(userName, mobile, returnDays);
                    break;
                case 4:
                    // Returning a phone
                    System.out.print("Enter Booking id : ");
                    String bookingId = scanner.next().trim();
                    System.out.print("Enter phone name : ");
                    String bookedPhone = scanner.next().trim();
                    returnService.returnPhone(bookedPhone, bookingId);
                    break;
                case 5:
                    // Exiting the application
                    System.out.println("Thanks for visiting application");
                    System.exit(200);
            }
        }
    }

    // Method to check if a string represents an integer
    private static boolean isInteger(String str) {
        return str.matches("-?\\d+");
    }
}

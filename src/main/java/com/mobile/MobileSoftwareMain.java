package com.mobile;

import com.mobile.util.Driver;
import com.mobile.util.Initializer;

public class MobileSoftwareMain {
    public static void main(String[] args) {
        Initializer.initializeApplication();
        Driver driver = new Driver();
        driver.drive();
    }
}

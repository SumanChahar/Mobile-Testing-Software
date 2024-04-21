package com.mobile.util;

import com.mobile.database.MobileDB;

/**
 * This class provides methods to initialize the application.
 */
public class Initializer {

    /**
     * Initializes the application by initializing the MobileDB.
     */
    public static void initializeApplication() {
        MobileDB.initializeMobileDB();
    }
}



package com.mobile.database;

import com.mobile.model.Mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileDB {
    private static final Map<String, Mobile> mobileDB = new HashMap<>();

    public static void initializeMobileDB() {
        mobileDB.put("Samsung Galaxy S9", new Mobile("M1", "Samsung Galaxy S9", "Galaxy S9 of Samsung", true, 1));
        mobileDB.put("Samsung Galaxy S8", new Mobile("M2", "Samsung Galaxy S8", " Samsung Galaxy S8", true, 2));
        mobileDB.put("Motorola Nexus 6", new Mobile("M3", "Motorola Nexus 6", "Motorola Nexus 6", true, 1));
        mobileDB.put("Oneplus 9", new Mobile("M4", "Oneplus 9", "Oneplus 9", true, 1));
        mobileDB.put("Apple iPhone 13", new Mobile("M5", "Apple iPhone 13", "Apple iPhone 13", true, 1));
        mobileDB.put("Apple iPhone 12", new Mobile("M6", "Apple iPhone 12", "Apple iPhone 12", true, 1));
        mobileDB.put("Apple iPhone 11", new Mobile("M7", "Apple iPhone 11", "Apple iPhone 11", true, 1));
        mobileDB.put("iPhone X", new Mobile("M8", "iPhone X", "iPhone X", true, 1));
        mobileDB.put("Nokia 3310", new Mobile("M8", "Nokia 3310", "Nokia 3310", true, 1));
    }

    public Mobile getMobileByName(String name) {
        return mobileDB.get(name);
    }

    public List<Mobile> getAllMobiles() {
        return mobileDB.values().stream().toList();
    }

    public void setMobileInDB(Mobile mobile) {
        mobileDB.put(mobile.getName(), mobile);
    }
}

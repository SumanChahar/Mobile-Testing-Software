package com.mobile.database;

import com.mobile.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private final static Map<String, User> userDB = new HashMap<>();

    public User getUserById(String id) {
        return userDB.get(id);
    }

    public void createUser(User user) {
        userDB.put(user.getId(), user);
        System.out.println("User with userId : " + user.getId() + " created");
    }
}

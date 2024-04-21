package com.mobile.service;

import com.mobile.dao.UserDAO;
import com.mobile.exception.UserAlreadyExistsException;
import com.mobile.exception.UserNotFoundException;
import com.mobile.model.User;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // For unit testing
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void createUser(String id, String name, String phone, String address) {
        try {
            userDAO.createUser(new User(id, name, phone, address));
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.out.println(userAlreadyExistsException.getMessage());
        }
    }

    public User getUserById(String id) {
        User user = userDAO.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id : " + id + " not found");
        }
        return user;
    }
}

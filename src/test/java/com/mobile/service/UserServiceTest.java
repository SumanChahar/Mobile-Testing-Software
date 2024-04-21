package com.mobile.service;

import com.mobile.dao.UserDAO;
import com.mobile.exception.UserAlreadyExistsException;
import com.mobile.exception.UserNotFoundException;
import com.mobile.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {
    private UserService userService;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        userDAO = mock(UserDAO.class);
        userService = new UserService(userDAO);
    }

    @Test
    public void testGetUserByIdUserExists() {
        User expectedUser = new User("abc", "John", "1234567890", "England");
        when(userDAO.getUserById("abc")).thenReturn(expectedUser);
        User actualUser = userService.getUserById("abc");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUserByIdUserNotExists() {
        String userId = "abc";
        when(userDAO.getUserById(userId)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    public void testCreateUserSuccess() {
        String id = "john";
        String name = "John";
        String phone = "1234567890";
        String address = "England";
        User user = new User(id, name, phone, address);
        doNothing().when(userDAO).createUser(user);
        userService.createUser(id, name, phone, address);
    }

    @Test
    public void testCreateUserError() {
        String id = "john";
        String name = "John";
        String phone = "1234567890";
        String address = "England";
        User user = new User(id, name, phone, address);
        doThrow(new UserAlreadyExistsException("User already exists")).when(userDAO).createUser(user);
        userService.createUser(id, name, phone, address);
    }
}

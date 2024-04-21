package com.mobile.dao;

import com.mobile.database.UserDB;
import com.mobile.exception.UserAlreadyExistsException;
import com.mobile.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserDAOTest {
    private UserDAO userDAO;
    private UserDB userDB;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
    userDB = mock(UserDB.class);
    userDAO = new UserDAO();
        // set the userDB instance using a setter method or reflection
        // Use reflection to inject the mock userDB
        Field field = UserDAO.class.getDeclaredField("userDB");
        field.setAccessible(true);
        field.set(userDAO, userDB);
    }

    @Test
    public void testCreateUserSuccess() {
        String id = "john";
        String name = "John";
        String phone = "1234567890";
        String address = "England";
        User user = new User(id, name, phone, address);
        //Mockito.when(userDAO.getUserById("john")).thenReturn(null);
        doNothing().when(userDB).createUser(user);
        userDAO.createUser(user);
        verify(userDB, times(1)).createUser(user);
    }

    @Test
    public void testCreateUserError() {
        String id = "john";
        String name = "John";
        String phone = "1234567890";
        String address = "England";
        User user = new User(id, name, phone, address);
        when(userDB.getUserById(id)).thenReturn(user);
        assertThrows(UserAlreadyExistsException.class,()-> userDAO.createUser(user) );
    }

    @Test
    public void testGetUserById() {
        when(userDB.getUserById(Mockito.anyString())).thenReturn(null);
        User user= userDAO.getUserById("john");
        assertNull(user);
    }

}


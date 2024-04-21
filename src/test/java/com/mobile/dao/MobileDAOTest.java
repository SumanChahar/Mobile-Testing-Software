package com.mobile.dao;

import com.mobile.dao.MobileDAO;
import com.mobile.database.MobileDB;
import com.mobile.exception.MobileNotFoundException;
import com.mobile.model.Mobile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MobileDAOTest {

    private MobileDAO mobileDAO;
    private MobileDB mobileDBMock;

    @BeforeEach

    public void setUp() {
        mobileDBMock = mock(MobileDB.class);
        mobileDAO = new MobileDAO();
        // Set the mocked MobileDB instance using reflection or setter method
        setInternalState(mobileDAO, "mobileDB", mobileDBMock);
    }

    @Test
    public void testGetAllAvailableMobiles() {
        // Arrange
        List<Mobile> allMobiles = new ArrayList<>();

        allMobiles.add(new Mobile("m1", "Iphone 13", "Iphone", true, 1));
        allMobiles.add(new Mobile("m2", "Iphone 12", "Iphone", false, 0));

        when(mobileDBMock.getAllMobiles()).thenReturn(allMobiles);

        List<Mobile> availableMobiles = mobileDAO.getAllAvailableMobiles();

        assertEquals(1, availableMobiles.size());
        assertTrue(availableMobiles.stream().allMatch(Mobile::getAvailable));
    }

    @Test
    public void testGetMobileByName() {
        // Arrange
        String name = "iPhone";
        Mobile expectedMobile = new Mobile("m1", "Iphone 13", "Iphone", true, 1);
        when(mobileDBMock.getMobileByName(name)).thenReturn(expectedMobile);

        Mobile actualMobile = mobileDAO.getMobileByName(name);

        assertEquals(expectedMobile, actualMobile);
    }

   @Test
    public void testGetMobileByName_NotFound() {
        // Arrange
        String name = "NonExistingMobile";
        when(mobileDBMock.getMobileByName(name)).thenReturn(null);

        assertThrows(MobileNotFoundException.class,()-> mobileDAO.getMobileByName(name));
    }

    @Test
    public void testSetMobileInDB() {
        // Arrange
        Mobile mobile =  new Mobile("newMob", "Iphone 10", "Iphone", true, 1);

        mobileDAO.setMobileInDB(mobile);

        // Assert
        verify(mobileDBMock, times(1)).setMobileInDB(mobile);
    }

    // Helper method for setting private fields using reflection
    private void setInternalState(Object object, String fieldName, Object value) {
        try {
            var field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

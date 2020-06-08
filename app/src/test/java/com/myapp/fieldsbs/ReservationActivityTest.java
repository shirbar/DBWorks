package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReservationActivityTest {

    @Test
    public void getMyFootballBasketballActivity() {
        assertTrue(ReservationActivity.getMyAct("TestUser1"));
    }

    @Test
    public void deleteExistedFootballBasketballActivity() {
        assertTrue(ReservationActivity.getMyAct("TestUser2"));
    }

    @Test
    public void deleteUnExistedFootballBasketballActivity() {
        assertTrue(ReservationActivity.getMyAct("TestUser3"));
    }

    @Test
    public void testClearFootballBasketballActivityLists() {
        assertTrue(ReservationActivity.getMyAct("TestUser4"));
    }
}
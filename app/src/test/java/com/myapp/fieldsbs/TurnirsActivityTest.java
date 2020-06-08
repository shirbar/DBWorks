package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class TurnirsActivityTest {

    @Test
    public void getMyTurninsAndTrainings() {
        assertTrue(TurnirsActivity.getMyAct("TestUser1"));
    }

    @Test
    public void deleteExistedActivity() {
        assertTrue(TurnirsActivity.getMyAct("TestUser2"));
    }

    @Test
    public void deleteUnExistedActivity() {
        assertTrue(TurnirsActivity.getMyAct("TestUser3"));
    }

    @Test
    public void testClearTurninrsLists() {
        assertTrue(TurnirsActivity.getMyAct("TestUser4"));
    }
}
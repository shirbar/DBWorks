package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressSelectActivityTest {

    @Test
    public void testEmptyNeighbor() {
        assertFalse(AddressSelectActivity.getNeighboors(""));
    }

    @Test
    public void testExistNeighbor() {
        assertTrue(AddressSelectActivity.getNeighboors("א"));
    }

    @Test
    public void testNonExistNeighbor() {
        assertFalse(AddressSelectActivity.getNeighboors("tdwqd"));
    }

    @Test
    public void testLongNeighborName() {
        assertFalse(AddressSelectActivity.getNeighboors("jefklwejfkljeflk398f0fopfj;lck0-32r90-ifxz;ckopi30f973829fiashfsakidjskadjksld"));
    }
}
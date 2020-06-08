package com.myapp.fieldsbs

import org.junit.Test;

import static org.junit.Assert.*;

class AddressSelectActivityTest extends groovy.util.GroovyTestCase {
    @Test
    void testExistNeighbor() {
        assertTrue(AddressSelectActivity.getNeighboors("א"));
    }
    @Test
    void testNonExistNeighbor() {
        assertFalse(AddressSelectActivity.getNeighboors("tdwqd"));
    }
}

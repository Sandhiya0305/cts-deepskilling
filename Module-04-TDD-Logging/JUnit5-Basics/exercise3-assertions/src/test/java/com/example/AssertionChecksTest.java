package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionChecksTest {
    private AssertionChecks c = new AssertionChecks();

    @Test
    void testEquals() {
        assertEquals(5, c.compute(2, 3));
    }

    @Test
    void testTrueFalse() {
        assertTrue(c.check(5));
        assertFalse(c.check(-1));
    }

    @Test
    void testNullNotNull() {
        assertNull(c.getObj());
        assertNotNull(new Object());
    }
}
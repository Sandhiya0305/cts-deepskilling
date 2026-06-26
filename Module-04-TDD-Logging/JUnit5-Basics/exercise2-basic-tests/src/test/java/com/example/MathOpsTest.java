package com.example;

import model.MathOps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathOpsTest {
    private MathOps ops;

    @BeforeEach
    void init() {
        ops = new MathOps();
    }

    @Test
    @DisplayName("add 2 nums")
    void testAdd() {
        assertEquals(5, ops.add(2, 3));
        assertEquals(0, ops.add(-1, 1));
    }

    @Test
    @DisplayName("subtract 2 nums")
    void testSub() {
        assertEquals(1, ops.subtract(3, 2));
        assertEquals(-1, ops.subtract(2, 3));
    }
}
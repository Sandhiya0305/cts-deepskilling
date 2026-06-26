package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator c;

    @BeforeEach
    void init() {
        c = new Calculator();
    }

    @Test
    @DisplayName("add 2 nums")
    void testAdd() {
        assertEquals(5, c.add(2, 3));
    }

    @Test
    @DisplayName("subtract 2 nums")
    void testSubtract() {
        assertEquals(1, c.subtract(3, 2));
    }

    @Test
    @DisplayName("multiply 2 nums")
    void testMultiply() {
        assertEquals(6, c.multiply(2, 3));
    }

    @Test
    @DisplayName("divide by zero throws")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> c.divide(4, 0));
    }

    @Test
    @DisplayName("divide works")
    void testDivide() {
        assertEquals(2.5, c.divide(5, 2), 0.01);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("add with param")
    void testAddParam(int n) {
        assertTrue(c.add(n, n) > 0);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "0, 0, 0", "-1, 1, 0"})
    @DisplayName("csv add checks")
    void testCsvAdd(int a, int b, int exp) {
        assertEquals(exp, c.add(a, b));
    }
}
package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorTest {
    private OrderItem item;

    @BeforeEach
    void setup() {
        item = new OrderItem();
        item.id = 1;
        item.price = 100;
    }

    @AfterEach
    void cleanup() {
        System.out.println("cleand");
    }

    @Test
    void arrangeActAssert() {
        double discounted = item.price * 0.9;
        assertEquals(90, discounted, 0.01);
    }
}
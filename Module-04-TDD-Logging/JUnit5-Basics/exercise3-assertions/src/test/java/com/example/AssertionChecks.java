package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionChecks {
    public int compute(int a, int b) {
        return a + b;
    }
    public boolean check(int n) {
        return n > 0;
    }
    public Object getObj() {
        return null;
    }
}
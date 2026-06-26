package com.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyServiceTest {

    @Test
    public void testMockingAndStubbing() {
        ExternalApi a = mock(ExternalApi.class);
        when(a.getData()).thenReturn("b");
        MyService c = new MyService(a);
        assertEquals("b", c.fetchData());
    }

    @Test
    public void testVerifyInteraction() {
        ExternalApi a = mock(ExternalApi.class);
        MyService b = new MyService(a);
        b.fetchData();
        verify(a).getData();
    }
}

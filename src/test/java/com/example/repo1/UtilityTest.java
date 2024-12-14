package com.example.repo1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilityTest {
    @Test
    public void testGetMessage() {
        assertEquals("Hello from Repo1!", Utility.getMessage());
    }
}

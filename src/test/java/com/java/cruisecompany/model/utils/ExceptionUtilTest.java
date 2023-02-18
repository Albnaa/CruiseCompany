package com.java.cruisecompany.model.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionUtilTest {
    @Test
    public void testGetRootMessage() {
        Throwable rootCause = new IllegalArgumentException("root cause");
        Throwable exception = new Exception("test", rootCause);
        String message = ExceptionUtil.getRootMessage(exception);
        assertEquals("root cause", message);
    }

    @Test
    public void testGetRootMessageNullPointerException() {
        assertThrows(NullPointerException.class, () -> ExceptionUtil.getRootMessage(null));
    }

    @Test
    public void testGetRootMessageWithExceptionWithoutMessage() {
        Throwable rootCause = new Throwable();
        Throwable exception = new Exception("test", rootCause);
        String message = ExceptionUtil.getRootMessage(exception);
        assertNull(message);
    }
}
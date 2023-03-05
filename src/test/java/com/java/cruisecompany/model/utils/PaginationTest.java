package com.java.cruisecompany.model.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaginationTest {
    @Mock
    HttpServletRequest request;
    @Test
    void testCalculatePages() {
        Pagination.calculatePages(request, 100);

        verify(request).setAttribute("offset", 0);
        verify(request).setAttribute("rows", 5);
        verify(request).setAttribute("numOfPages", 20);
        verify(request).setAttribute("currPage", 1);
        verify(request).setAttribute("numOfRows", 100L);
        verify(request).setAttribute("lowerBound", 1);
        verify(request).setAttribute("upperBound", 7);
    }

    @Test
    void testParseIntFromString() {
        long result = Pagination.parseIntFromString("10", 5);
        assertEquals(10, result);

        result = Pagination.parseIntFromString("not a number", 5);
        assertEquals(5, result);

        result = Pagination.parseIntFromString("-10", 5);
        assertEquals(5, result);
    }

    @Test
    void testCalculateLowerBound() {
        long result = Pagination.calculateLowerBound(4, 10);
        assertEquals(1, result);

        result = Pagination.calculateLowerBound(7, 10);
        assertEquals(4, result);

        result = Pagination.calculateLowerBound(7, 6);
        assertEquals(1, result);
    }
}
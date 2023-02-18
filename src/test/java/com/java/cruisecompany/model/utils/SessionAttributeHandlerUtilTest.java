package com.java.cruisecompany.model.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SessionAttributeHandlerUtilTest {
    @Test
    void testSetAttrFromReqToSession() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        String queryString = "sort=asc&order=1&roleF=admin&userF=user&durationF=5&startDateF=2022-05-01&nameF=abc";
        when(request.getQueryString()).thenReturn(queryString);
        when(request.getSession()).thenReturn(session);

        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);

        verify(session).setAttribute("sort", "asc");
        verify(session).setAttribute("order", "1");
        verify(session).setAttribute("roleF", "admin");
        verify(session).setAttribute("userF", "user");
        verify(session).setAttribute("durationF", "5");
        verify(session).setAttribute("startDateF", "2022-05-01");
        verify(session).setAttribute("nameF", "abc");
    }

    @Test
    void testBuildAttrMapFromReq() {
        // create a mock HttpServletRequest object
        HttpServletRequest request = mock(HttpServletRequest.class);

        // set up the query string for the mock request object
        String queryString = "sort=asc&order=1&roleF=admin&userF=user&durationF=5&startDateF=2022-05-01&nameF=abc";
        when(request.getQueryString()).thenReturn(queryString);

        // call the buildAttrMapFromReq method
        Map<String, String> attributes = SessionAttributeHandlerUtil.buildAttrMapFromReq(request);

        // verify that the attributes were built correctly
        assertEquals(7, attributes.size());
        assertEquals("asc", attributes.get("sort"));
        assertEquals("1", attributes.get("order"));
        assertEquals("admin", attributes.get("roleF"));
        assertEquals("user", attributes.get("userF"));
        assertEquals("5", attributes.get("durationF"));
        assertEquals("2022-05-01", attributes.get("startDateF"));
        assertEquals("abc", attributes.get("nameF"));
    }
}
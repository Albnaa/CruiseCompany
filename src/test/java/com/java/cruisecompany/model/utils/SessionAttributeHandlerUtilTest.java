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

        String queryString = "sort=user.id&order=desc&roleF=admin&userF=Bob&durationF=5&startDateF=2022-05-01&nameF=abc";
        when(request.getQueryString()).thenReturn(queryString);
        when(request.getSession()).thenReturn(session);

        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);

        verify(session).setAttribute("sort", "user.id");
        verify(session).setAttribute("order", "desc");
        verify(session).setAttribute("roleF", "admin");
        verify(session).setAttribute("userF", "Bob");
        verify(session).setAttribute("durationF", "5");
        verify(session).setAttribute("startDateF", "2022-05-01");
        verify(session).setAttribute("nameF", "abc");
    }

    @Test
    void testBuildAttrMapFromReq() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        String queryString = "sort=user.id&order=desc&roleF=admin&userF=Bob&durationF=5&startDateF=2022-05-01&nameF=abc";
        when(request.getQueryString()).thenReturn(queryString);

        Map<String, String> attributes = SessionAttributeHandlerUtil.buildAttrMapFromReq(request);

        assertEquals(7, attributes.size());
        assertEquals("user.id", attributes.get("sort"));
        assertEquals("desc", attributes.get("order"));
        assertEquals("admin", attributes.get("roleF"));
        assertEquals("Bob", attributes.get("userF"));
        assertEquals("5", attributes.get("durationF"));
        assertEquals("2022-05-01", attributes.get("startDateF"));
        assertEquals("abc", attributes.get("nameF"));
    }
}
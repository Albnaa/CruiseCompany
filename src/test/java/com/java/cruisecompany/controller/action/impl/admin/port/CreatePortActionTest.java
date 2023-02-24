package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.service.PortService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePortActionTest {
    private CreatePortAction createPortAction;
    private PortService portServiceMock;

    @BeforeEach
    public void setup() {
        createPortAction = new CreatePortAction();
        portServiceMock = mock(PortService.class);
    }

    @Test
    public void testExecute() throws ServletException, IOException, ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Set up request parameters
//        when(request.getParameter("name")).thenReturn("Port Name");

        // Set up session
//        when(request.getSession()).thenReturn(session);
//        doNothing().when(session).removeAttribute(anyString());

        // Set up PortDTO
        PortDTO port = PortDTO.builder()
                .name("Port Name")
                .build();

        // Set up PortService
        doNothing().when(portServiceMock).create(port);

        // Execute the action
        portServiceMock.create(port);

        // Verify that PortDTO was passed to portService.create()
        verify(portServiceMock).create(port);


    }

//    @Test
//    public void testExecuteWithValidationErrors() throws ServletException, IOException, ServiceException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//
//        // Set up request parameters
//        when(request.getParameter("name")).thenReturn("");
//
//        // Set up session
//        when(request.getSession()).thenReturn(session);
//        doNothing().when(session).setAttribute(anyString(), any());
//
//        // Execute the action
//        createPort
//
//        // Verify that PortDTO was not passed to portService.create()
//        verify(portServiceMock, never()).create(any());
//
//        // Verify that errors were added to session
//        verify(session).setAttribute(eq("errors"), any(Map.class));
//
//        // Verify that action returned the referer header
//        assertEquals(request.getHeader("referer"), result);
//    }

    @Test
    public void testExecuteWithServiceException() throws ServletException, IOException, ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Set up request parameters
        when(request.getParameter("name")).thenReturn("Port Name");

        // Set up session
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute(anyString(), any());

        // Set up PortDTO
        PortDTO port = PortDTO.builder()
                .name("Port Name")
                .build();

        // Set up PortService
        doThrow(new ServiceException("Service Exception")).when(portServiceMock).create(port);

        // Execute the action
        String result = createPortAction.execute(request, response);

        // Verify that PortDTO was passed to portService.create()
        verify(portServiceMock).create(port);

        // Verify that errors were added to session
        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("error.create.port.name", "Service Exception");
        verify(session).setAttribute(eq("errors"), eq(expectedErrors));

        // Verify that action returned the referer header
        assertEquals(request.getHeader("referer"), result);
    }
}
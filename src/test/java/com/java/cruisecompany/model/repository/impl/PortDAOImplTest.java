package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.repository.PortDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PortDAOImplTest {
    @Mock
    private PortDAO portDAO;

    @InjectMocks
    private PortDAOImpl portDAOImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById_ShouldReturnExpectedPort() {
        Port expectedPort = new Port(1, "Boston");
        when(portDAO.findById(1)).thenReturn(Optional.of(expectedPort));

        Optional<Port> actualPort = portDAO.findById(1);

        assertTrue(actualPort.isPresent());
        assertEquals(expectedPort, actualPort.get());
    }
    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }


    @Test
    void findById_Null_User() {
        long portId = 300;
        Port port = portDAO.findById(portId).orElse(null);
        assertNull(port);
    }

    @Test
    void findAll() {
    }

    @Test
    void findSorted() {
    }

    @Test
    void getNumOfRows() {
    }

    @Test
    void mapToEntity() {
    }
}
package com.java.cruisecompany.model.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ServiceFactoryTest {
    ServiceFactory serviceFactory;

    @BeforeEach
    void setUp() {
        this.serviceFactory = ServiceFactory.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(serviceFactory);
    }

    @Test
    void getPortService() {
        assertNotNull(serviceFactory.getPortService());
    }

    @Test
    void getRouteService() {
        assertNotNull(serviceFactory.getRouteService());
    }

    @Test
    void getShipService() {
        assertNotNull(serviceFactory.getShipService());
    }

    @Test
    void getTicketService() {
        assertNotNull(serviceFactory.getTicketService());
    }

    @Test
    void getUserService() {
        assertNotNull(serviceFactory.getUserService());
    }
}
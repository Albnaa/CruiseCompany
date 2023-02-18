package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.repository.*;
import com.java.cruisecompany.model.repository.impl.DAOFactory;
import com.java.cruisecompany.model.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
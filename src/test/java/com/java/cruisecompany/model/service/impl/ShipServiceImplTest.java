package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchShipException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.repository.ShipDAO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.service.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipServiceImplTest {
    
    @Mock
    ShipDAO shipDAO;
    @Mock
    RouteService routeService;
    
    private ShipService shipService;

    @BeforeEach
    void setUp() {
        this.shipService = new ShipServiceImpl(shipDAO, routeService);
    }

    @Test
    void testCreateSuccess() throws ServiceException {
        shipService.create(getShipDTO());
        verify(shipDAO).create(any(Ship.class));
    }

    @Test
    void testCreateServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).create(any(Ship.class));

        assertThrows(ServiceException.class, () -> shipService.create(getShipDTO()));
        verify(shipDAO).create(any(Ship.class));
    }

    @Test
    void testUpdateSuccess() throws ServiceException {
        shipService.update(getShipDTO());
        verify(shipDAO).update(any(Ship.class));
    }

    @Test
    void testUpdateServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).update(any(Ship.class));

        assertThrows(ServiceException.class, () -> shipService.update(getShipDTO()));
        verify(shipDAO).update(any(Ship.class));
    }

    @Test
    void testDeleteSuccess() throws ServiceException {
        shipService.delete(1L);
        verify(shipDAO).delete(eq(1L));
    }

    @Test
    void testDeleteServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).delete(eq(1L));

        assertThrows(ServiceException.class, () -> shipService.delete(1L));
        verify(shipDAO).delete(eq(1L));
    }
    
    @Test
    void updateImageSuccess() throws ServiceException {
        shipService.updateImage(1L, "Path");
        verify(shipDAO).updateImage(eq(1L), any(String.class));
    }

    @Test
    void updateImageServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).updateImage(eq(1L), any(String.class));

        assertThrows(ServiceException.class, () -> shipService.updateImage(1L, "Path"));
        verify(shipDAO).updateImage(eq(1L), any(String.class));
    }

    @Test
    void findByIdSuccess() throws ServiceException {
        when(shipDAO.findById(eq(1L))).thenReturn(Optional.of(getShip()));
        when(routeService.findById((eq(1L)))).thenReturn(Optional.of(getRouteDTO()));

        Optional<ShipDTO> actualShip = shipService.findById(1L);

        assertTrue(actualShip.isPresent());
        assertEquals(getShipDTO(), actualShip.get());
        verify(shipDAO).findById(eq(1L));
        verify(routeService).findById(eq(1L));
    }

    @Test
    void findByIdNoSuchShipException() {
        when(shipDAO.findById(eq(1L))).thenReturn(Optional.empty());
        assertThrows(NoSuchShipException.class, () -> shipService.findById(1L));
    }

    @Test
    void findByIdServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).findById(eq(1L));

        assertThrows(ServiceException.class, () -> shipService.findById(1L));
        verify(shipDAO).findById(eq(1L));
    }

    @Test
    void findAll() throws ServiceException {
        List<Ship> ships = Collections.singletonList(getShip());
        List<ShipDTO> shipDTOs = Collections.singletonList(getShipDTO());

        when(shipDAO.findAll()).thenReturn(ships);

        assertIterableEquals(shipService.findAll(), shipDTOs);
        verify(shipDAO).findAll();
    }

    @Test
    void findAllServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).findAll();

        assertThrows(ServiceException.class, () -> shipService.findAll());
        verify(shipDAO).findAll();
    }

    @Test
    void findSortedWithRoutesSuccess() throws ServiceException {
        List<Ship> ships = Collections.singletonList(getShip());
        List<ShipDTO> shipDTOs = Collections.singletonList(getShipDTO());

        when(shipDAO.findSortedWithRoutes(any(String.class))).thenReturn(ships);
        when(routeService.findById(eq(1L))).thenReturn(Optional.of(getRouteDTO()));

        assertIterableEquals(shipService.findSortedWithRoutes("String"), shipDTOs);
        verify(shipDAO).findSortedWithRoutes(any(String.class));
    }

    @Test
    void findSortedWithRoutesServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).findSortedWithRoutes(any(String.class));

        assertThrows(ServiceException.class, () -> shipService.findSortedWithRoutes("String"));
        verify(shipDAO).findSortedWithRoutes(any(String.class));
    }

    @Test
    void findSortedSuccess() throws ServiceException {
        List<Ship> ships = Collections.singletonList(getShip());
        List<ShipDTO> shipDTOs = Collections.singletonList(getShipDTO());

        when(shipDAO.findSorted(any(String.class))).thenReturn(ships);

        assertIterableEquals(shipService.findSorted("String"), shipDTOs);
        verify(shipDAO).findSorted(any(String.class));
    }

    @Test
    void findSortedServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).findSorted(any(String.class));

        assertThrows(ServiceException.class, () -> shipService.findSorted("String"));
        verify(shipDAO).findSorted(any(String.class));
    }

    @Test
    void getNumOfRowsSuccess() throws ServiceException {
        long rows = 5L;

        when(shipDAO.getNumOfRows(any(String.class))).thenReturn(rows);

        assertEquals(rows, shipService.getNumOfRows("String"));
        verify(shipDAO).getNumOfRows(any(String.class));
    }

    @Test
    void getNumOfRowsServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).getNumOfRows(any(String.class));

        assertThrows(ServiceException.class, () -> shipService.getNumOfRows("String"));
        verify(shipDAO).getNumOfRows(any(String.class));
    }



    @Test
    void getNumOfRowsWithRoutesSuccess() throws ServiceException {
        long rows = 5L;

        when(shipDAO.getNumOfRowsWithRoutes(any(String.class))).thenReturn(rows);

        assertEquals(rows, shipService.getNumOfRowsWithRoutes("String"));
        verify(shipDAO).getNumOfRowsWithRoutes(any(String.class));
    }

    @Test
    void getNumOfRowsWithRoutesServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).getNumOfRowsWithRoutes(any(String.class));

        assertThrows(ServiceException.class, () -> shipService.getNumOfRowsWithRoutes("String"));
        verify(shipDAO).getNumOfRowsWithRoutes(any(String.class));
    }

    @Test
    void addRouteSuccess() throws ServiceException {
        shipService.addRoute(1L, 1L);
        verify(shipDAO).addRoute(eq(1L), eq(1L));
    }

    @Test
    void addRouteServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).addRoute(eq(1L), eq(1L));

        assertThrows(ServiceException.class, () -> shipService.addRoute(1L, 1L));
        verify(shipDAO).addRoute(eq(1L), eq(1L));
    }

    @Test
    void deleteRouteSuccess() throws ServiceException {
        shipService.deleteRoute(1L);
        verify(shipDAO).deleteRoute(eq(1L));
    }

    @Test
    void deleteRouteServiceException() {
        Mockito.doThrow(new DAOException()).when(shipDAO).deleteRoute(eq(1L));

        assertThrows(ServiceException.class, () -> shipService.deleteRoute(1L));
        verify(shipDAO).deleteRoute(eq(1L));
    }
    private Ship getShip() {
        return Ship.builder()
                .id(1)
                .name("Ship")
                .capacity(500)
                .visited_ports(50)
                .staff(50)
                .imagePath("Image")
                .route(getRoute())
                .build();
    }

    private ShipDTO getShipDTO() {
        return ShipDTO.builder()
                .id(1)
                .name("Ship")
                .capacity(500)
                .visitedPorts(50)
                .staff(50)
                .imagePath("Image")
                .route(getRouteDTO())
                .build();
    }

    private Route getRoute() {
        return Route.builder()
                .id(1)
                .name("North America Cruise")
                .startOfCruise(LocalDate.parse("2023-02-01"))
                .endOfCruise(LocalDate.parse("2023-02-10"))
                .duration(9)
                .price(BigDecimal.valueOf(2400))
                .build();
    }

    private RouteDTO getRouteDTO() {
        return RouteDTO.builder()
                .id(1)
                .name("North America Cruise")
                .startOfCruise(LocalDate.parse("2023-02-01"))
                .endOfCruise(LocalDate.parse("2023-02-10"))
                .duration(9)
                .price(BigDecimal.valueOf(2400))
                .build();
    }
}
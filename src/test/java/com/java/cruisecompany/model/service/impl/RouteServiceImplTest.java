package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchRouteException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import com.java.cruisecompany.model.repository.RouteDAO;
import com.java.cruisecompany.model.service.RouteService;
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
class RouteServiceImplTest {

    @Mock
    RouteDAO routeDAO;

    private RouteService routeService;

    @BeforeEach
    void setUp() {
        this.routeService = new RouteServiceImpl(routeDAO);
    }

    @Test
    void testCreateSuccess() throws ServiceException {
        routeService.create(getRouteDTO());
        verify(routeDAO).create(any(Route.class));
    }

    @Test
    void testCreateServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).create(any(Route.class));

        assertThrows(ServiceException.class, () -> routeService.create(getRouteDTO()));
        verify(routeDAO).create(any(Route.class));
    }

    @Test
    void testUpdateSuccess() throws ServiceException {
        routeService.update(getRouteDTO());
        verify(routeDAO).update(any(Route.class));
    }

    @Test
    void testUpdateServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).update(any(Route.class));

        assertThrows(ServiceException.class, () -> routeService.update(getRouteDTO()));
        verify(routeDAO).update(any(Route.class));
    }

    @Test
    void testDeleteSuccess() throws ServiceException {
        routeService.delete(1L);
        verify(routeDAO).delete(eq(1L));
    }

    @Test
    void testDeleteServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).delete(eq(1L));

        assertThrows(ServiceException.class, () -> routeService.delete(1L));
        verify(routeDAO).delete(eq(1L));
    }

    @Test
    void testFindByIdSuccess() throws ServiceException {
        when(routeDAO.findById(eq(1L))).thenReturn(Optional.of(getRoute()));
        when(routeDAO.getRouteWaypoints(eq(1L))).thenReturn(Collections.singletonList(getWayPoint()));

        Optional<RouteDTO> actualRoute = routeService.findById(1L);

        assertTrue(actualRoute.isPresent());
        assertEquals(getRouteDTO(), actualRoute.get());
        verify(routeDAO).findById(1L);
    }

    @Test
    void testFindByIdNoSuchRouteException() {
        when(routeDAO.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchRouteException.class, () -> routeService.findById(1L));
    }

    @Test
    void testFindByIdServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).findById(eq(1L));

        assertThrows(ServiceException.class, () -> routeService.findById(1L));
        verify(routeDAO).findById(eq(1L));
    }

    @Test
    void testFindAllSuccess() throws ServiceException {
        List<Route> routes = Collections.singletonList(getRoute());
        List<RouteDTO> routeDTOs = Collections.singletonList(getRouteDTO());

        when(routeDAO.findAll()).thenReturn(routes);
        when(routeDAO.getRouteWaypoints(eq(1L))).thenReturn(Collections.singletonList(getWayPoint()));

        assertIterableEquals(routeService.findAll(), routeDTOs);
        verify(routeDAO).findAll();
    }

    @Test
    void testFindAllServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).findAll();

        assertThrows(ServiceException.class, () -> routeService.findAll());
        verify(routeDAO).findAll();
    }

    @Test
    void testFindSortedSuccess() throws ServiceException {
        List<Route> routes = Collections.singletonList(getRoute());
        List<RouteDTO> routeDTOs = Collections.singletonList(getRouteDTO());

        when(routeDAO.findSorted(any(String.class))).thenReturn(routes);
        when(routeDAO.getRouteWaypoints(eq(1L))).thenReturn(Collections.singletonList(getWayPoint()));

        assertIterableEquals(routeService.findSorted("String"), routeDTOs);
        verify(routeDAO).findSorted(any(String.class));
    }

    @Test
    void testFindSortedServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).findSorted(any(String.class));

        assertThrows(ServiceException.class, () -> routeService.findSorted("String"));
        verify(routeDAO).findSorted(any(String.class));
    }

    @Test
    void testGetNumOfRowsSuccess() throws ServiceException {
        long rows = 5L;

        when(routeDAO.getNumOfRows(any(String.class))).thenReturn(rows);

        assertEquals(rows, routeService.getNumOfRows("String"));
        verify(routeDAO).getNumOfRows(any(String.class));
    }

    @Test
    void testGetNumOfRowsServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).getNumOfRows(any(String.class));

        assertThrows(ServiceException.class, () -> routeService.getNumOfRows("String"));
        verify(routeDAO).getNumOfRows(any(String.class));
    }

    @Test
    void testDeleteWaypointSuccess() throws ServiceException {
        routeService.deleteWaypoint(1L, 1L);
        verify(routeDAO).deleteWaypoint(eq(1L), eq(1L));
    }

    @Test
    void testDeleteWaypointServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO).deleteWaypoint(eq(1L), eq(1L));

        assertThrows(ServiceException.class, () -> routeService.deleteWaypoint(1L, 1L));
        verify(routeDAO).deleteWaypoint(eq(1L), eq(1L));
    }

    @Test
    void testAddWaypointSuccess() throws ServiceException {
        routeService.addWaypoint(1L, 1L, LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 5, 6));
        verify(routeDAO).addWaypoint(eq(1L), eq(1L), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void testAddWaypointServiceException() {
        Mockito.doThrow(new DAOException()).when(routeDAO)
                .addWaypoint(eq(1L), eq(1L), any(LocalDate.class), any(LocalDate.class));

        assertThrows(ServiceException.class, () -> routeService.addWaypoint(1L, 1L,
                LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 5, 6)));
        verify(routeDAO).addWaypoint(eq(1L), eq(1L), any(LocalDate.class), any(LocalDate.class));
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
                .waypoints(Collections.singletonList(getWayPoint()))
                .numOfPorts(1L)
                .build();
    }

    private Waypoint getWayPoint() {
        return Waypoint.builder()
                .port(getPort())
                .arriveTime(LocalDate.parse("2023-02-01"))
                .departureTime(LocalDate.parse("2023-02-01"))
                .build();
    }

    private Port getPort() {
        return Port.builder()
                .id(1)
                .name("Boston")
                .build();
    }
}
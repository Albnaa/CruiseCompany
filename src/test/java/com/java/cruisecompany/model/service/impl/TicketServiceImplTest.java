package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchTicketException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.dao.TicketDAO;
import com.java.cruisecompany.model.dao.UserDAO;
import com.java.cruisecompany.model.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    TicketDAO ticketDAO;
    @Mock
    UserDAO userDAO;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        this.ticketService = new TicketServiceImpl(ticketDAO, userDAO);
    }

    @Test
    void testCreateSuccess() throws ServiceException {
        ticketService.create(getTicketDTO());
        verify(ticketDAO).create(any(Ticket.class));
    }

    @Test
    void testCreateServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).create(any(Ticket.class));

        assertThrows(ServiceException.class, () -> ticketService.create(getTicketDTO()));
        verify(ticketDAO).create(any(Ticket.class));
    }

    @Test
    void testUpdateSuccess() throws ServiceException {
        ticketService.update(getTicketDTO());
        verify(ticketDAO).update(any(Ticket.class));
    }

    @Test
    void testUpdateServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).update(any(Ticket.class));

        assertThrows(ServiceException.class, () -> ticketService.update(getTicketDTO()));
        verify(ticketDAO).update(any(Ticket.class));
    }

    @Test
    void testDeleteSuccess() throws ServiceException {
        ticketService.delete(1L);
        verify(ticketDAO).delete(eq(1L));
    }

    @Test
    void testDeleteServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).delete(eq(1L));

        assertThrows(ServiceException.class, () -> ticketService.delete(1L));
        verify(ticketDAO).delete(eq(1L));
    }

    @Test
    void testFindByIdSuccess() throws ServiceException {
        when(ticketDAO.findById(eq(1L))).thenReturn(Optional.of(getTicket()));

        Optional<TicketDTO> actualTicket = ticketService.findById(1L);

        assertTrue(actualTicket.isPresent());
        assertEquals(getTicketDTO(), actualTicket.get());
        verify(ticketDAO).findById(1L);
    }

    @Test
    void testFindByIdNoSuchTicketException() {
        when(ticketDAO.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchTicketException.class, () -> ticketService.findById(1L));
    }

    @Test
    void testFindByIdServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).findById(eq(1L));

        assertThrows(ServiceException.class, () -> ticketService.findById(1L));
        verify(ticketDAO).findById(eq(1L));
    }

    @Test
    void testFindAllSuccess() throws ServiceException {
        List<Ticket> tickets = Collections.singletonList(getTicket());
        List<TicketDTO> ticketDTOs = Collections.singletonList(getTicketDTO());

        when(ticketDAO.findAll()).thenReturn(tickets);

        assertIterableEquals(ticketDTOs, ticketService.findAll());
        verify(ticketDAO).findAll();
    }

    @Test
    void testFindAllServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).findAll();

        assertThrows(ServiceException.class, () -> ticketService.findAll());
        verify(ticketDAO).findAll();
    }

    @Test
    void testFindSortedSuccess() throws ServiceException {
        List<Ticket> tickets = Collections.singletonList(getTicket());
        List<TicketDTO> ticketDTOs = Collections.singletonList(getTicketDTO());

        when(ticketDAO.findSorted(any(String.class))).thenReturn(tickets);

        assertIterableEquals(ticketDTOs, ticketService.findSorted("String"));
        verify(ticketDAO).findSorted(any(String.class));
    }

    @Test
    void testFindSortedServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).findSorted(any(String.class));

        assertThrows(ServiceException.class, () -> ticketService.findSorted("String"));
        verify(ticketDAO).findSorted(any(String.class));
    }

    @Test
    void testUpdateTicketStatusSuccess() throws ServiceException {
        ticketService.updateTicketStatus(1L, Status.UNCHECKED);
        verify(ticketDAO).updateTicketStatus(eq(1L), any(Status.class));
    }

    @Test
    void testUpdateTicketStatusServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).updateTicketStatus(eq(1L), any(Status.class));

        assertThrows(ServiceException.class, () -> ticketService.updateTicketStatus(1L, Status.UNCHECKED));
        verify(ticketDAO).updateTicketStatus(eq(1L), any(Status.class));
    }

    @Test
    void testPayForTicketSuccess() throws SQLException, ServiceException {
//        when(userDAO.findById(1L)).thenReturn(Optional.of(getUser()));
//        when(ticketDAO.findById(1L)).thenReturn(Optional.of(getTicket()));
//
//        Connection connection = mock(Connection.class);
//        DBManager dbManager = mock(DBManager.class);
//
//        when(DBManager.getConnection()).thenReturn(connection);
////        when(connection.setAutoCommit(false)).thenReturn(false);
//
//        ticketService.payForTicket(1L, 1L);
//
//        verify(userDAO).findById(eq(1L));
//        verify(ticketDAO).findById(eq(1L));
//        verify(connection).close();
//        verify(connection).setAutoCommit(true);
    }

    @Test
    void testPayForTicketNoSuchUserException() throws SQLException, ServiceException {

    }

    @Test
    void testPayForTicketServiceException() {

    }

    @Test
    void testGetNumOfRowsSuccess() throws ServiceException {
        long rows = 5L;

        when(ticketDAO.getNumOfRows(any(String.class))).thenReturn(rows);

        assertEquals(rows, ticketService.getNumOfRows("String"));
        verify(ticketDAO).getNumOfRows(any(String.class));
    }

    @Test
    void testGetNumOfRowsServiceException() {
        Mockito.doThrow(new DAOException()).when(ticketDAO).getNumOfRows(any(String.class));

        assertThrows(ServiceException.class, () -> ticketService.getNumOfRows("String"));
        verify(ticketDAO).getNumOfRows(any(String.class));
    }

    @Test
    void testCompleteTicketSuccess() {
        ticketService.completeTickets();
        verify(ticketDAO).findAll();
    }

    private TicketDTO getTicketDTO() {
        return TicketDTO.builder()
                .id(1)
                .passengersCount(1)
                .price(BigDecimal.valueOf(1000))
                .documentPath("Path")
                .status(Status.UNCHECKED)
                .user(getUserDTO())
                .ship(getShipDTO())
                .build();
    }

    private Ticket getTicket() {
        return Ticket.builder()
                .id(1)
                .passengersCount(1)
                .price(BigDecimal.valueOf(1000))
                .documentPath("Path")
                .status(Status.UNCHECKED)
                .user(getUser())
                .ship(getShip())
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(1)
                .login("admin")
                .email("admin@gmail.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .balance(BigDecimal.valueOf(1000))
                .role(Role.ADMIN)
                .build();
    }

    private UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(1)
                .login("admin")
                .email("admin@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .balance(BigDecimal.valueOf(1000))
                .role(Role.ADMIN)
                .build();
    }

    private Ship getShip() {
        return Ship.builder()
                .id(1)
                .name("Ship")
                .capacity(500)
                .visited_ports(50)
                .staff(50)
                .imagePath("Image")
                .route(null)
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
                .route(null)
                .build();
    }
}
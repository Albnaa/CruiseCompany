package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchTicketException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.connectionpool.DBManager;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.dao.TicketDAO;
import com.java.cruisecompany.model.dao.UserDAO;
import com.java.cruisecompany.model.service.TicketService;
import com.java.cruisecompany.model.utils.MapperDTO;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOToTicket;
import static com.java.cruisecompany.model.utils.MapperDTO.mapTicketToDTO;

/**
 * This class implements the TicketService interface and provides methods for creating, updating, deleting,
 * finding and sorting Tickets. It also provides a method to get the number of rows in the database matching a given query.
 * <p>
 * The class uses a TicketDAO and UserDAO objects to access the database and Log4j2 for logging.
 */
@Log4j2
public class TicketServiceImpl implements TicketService {
    private final TicketDAO ticketDAO;
    private final UserDAO userDAO;

    /**
     * Constructs a new TicketServiceImpl with the given TicketDAO.
     *
     * @param ticketDAO the TicketDAO to use for database access.
     * @param userDAO the UserDAO to use for database access.
     */
    TicketServiceImpl(TicketDAO ticketDAO, UserDAO userDAO) {
        this.ticketDAO = ticketDAO;
        this.userDAO = userDAO;
    }

    /**
     * Creates a new Ticket in the database.
     *
     * @param ticketDTO the TicketDTO object to be created
     * @throws ServiceException if an error occurs while creating the TicketDTO object in the database
     */
    @Override
    public void create(TicketDTO ticketDTO) throws ServiceException {
        try {
            ticketDAO.create(mapDTOToTicket(ticketDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates an existing Ticket in the database.
     *
     * @param ticketDTO the TicketDTO object to be updated
     * @throws ServiceException if an error occurs while updating the TicketDTO object in the database
     */
    @Override
    public void update(TicketDTO ticketDTO) throws ServiceException {
        try {
            ticketDAO.update(mapDTOToTicket(ticketDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes an existing Ticket from the database by id.
     *
     * @param id the id of the TicketDTO object to be deleted
     * @throws ServiceException if an error occurs while deleting the TicketDTO object from the database
     */
    @Override
    public void delete(long id) throws ServiceException {
        try {
            ticketDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds an existing Ticket in the database by id and returns it wrapped in an Optional.
     *
     * @param id the id of the TicketDTO object to be found
     * @return an Optional containing the found TicketDTO object, or an empty Optional if the TicketDTO was not found
     * @throws ServiceException if an error occurs while finding the TicketDTO object in the database
     */
    @Override
    public Optional<TicketDTO> findById(long id) throws ServiceException {
        TicketDTO ticketDTO;
        try {
            ticketDTO = mapTicketToDTO(ticketDAO.findById(id).orElseThrow(NoSuchTicketException::new));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(ticketDTO);
    }

    /**
     * Finds all Ticket objects in the database and returns them as a List.
     *
     * @return a List containing all TicketDTO objects in the database
     * @throws ServiceException if an error occurs while finding the TicketDTO objects in the database
     */
    @Override
    public List<TicketDTO> findAll() throws ServiceException {
        List<TicketDTO> ticketDTOs;
        try {
            ticketDTOs = ticketDAO.findAll().stream()
                    .map(MapperDTO::mapTicketToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return ticketDTOs;
    }

    /**
     * Retrieves a sorted list of all Tickets based on the given query.
     *
     * @param query the query to sort by.
     * @return a list of TicketDTOs sorted by the given query.
     * @throws ServiceException if there is an error retrieving the sorted list from the persistence layer.
     */
    @Override
    public List<TicketDTO> findSorted(String query) throws ServiceException {
        List<TicketDTO> ticketDTOs;
        try {
            ticketDTOs = ticketDAO.findSorted(query).stream()
                    .map(MapperDTO::mapTicketToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return ticketDTOs;
    }

    /**
     * Updates the status of a ticket with the given id.
     *
     * @param ticketId the id of the ticket to update
     * @param status   the new status to set for the ticket
     * @throws ServiceException if an error occurs while updating the ticket status
     */
    @Override
    public void updateTicketStatus(long ticketId, Status status) throws ServiceException {
        try {
            ticketDAO.updateTicketStatus(ticketId, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Pays for a ticket with the given id for the user with the given id by deducting the ticket price from the user's balance.
     *
     * @param userId   the id of the user paying for the ticket
     * @param ticketId the id of the ticket to pay for
     * @throws ServiceException if an error occurs while paying for the ticket
     */
    @Override
    public void payForTicket(long userId, long ticketId) throws ServiceException {
        Connection con = null;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            try {
                User user = userDAO.findById(userId).orElseThrow(NoSuchUserException::new);
                Ticket ticket = ticketDAO.findById(ticketId).orElseThrow(NoSuchTicketException::new);
                if (user.getBalance().compareTo(ticket.getPrice()) < 0) {
                    throw new ServiceException("error.topUp.user.insufficientBalance");
                }
                ticketDAO.updateTicketStatus(ticketId, Status.PAID);
                userDAO.deductFromBalance(userId, ticket.getPrice());
                con.commit();
            } catch (DAOException | NoSuchTicketException | NoSuchUserException e) {
                con.rollback();
                throw new ServiceException(e);
            }
        } catch (SQLException e) {
            log.error("Error in during paying for ticket -> " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Error while closing the database connection in pay for ticket transaction");
            }
        }
    }

    /**
     * Retrieves the number of rows from the {@link TicketDAO} based on the given query.
     *
     * @param query the query to count the number of rows for.
     * @return the number of rows that match the query.
     * @throws ServiceException if there is an error retrieving the number of rows from the persistence layer.
     */
    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return ticketDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates the status of all tickets whose status is not already completed and whose ship has passed its end of cruise
     * date to complete.
     */
    @Override
    public void completeTickets() {
        ticketDAO.findAll().stream()
                .filter(t -> t.getStatus() != Status.COMPLETED)
                .filter(t -> (t.getShip().getRoute().getEndOfCruise().isBefore(LocalDate.now())))
                .forEach(t -> ticketDAO.updateTicketStatus(t.getId(), Status.COMPLETED));
    }
}

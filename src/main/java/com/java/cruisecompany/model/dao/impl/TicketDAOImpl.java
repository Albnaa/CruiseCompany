package com.java.cruisecompany.model.dao.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.entity.Ticket;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.dao.GenericDAO;
import com.java.cruisecompany.model.dao.TicketDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.dao.constant.TableFields.*;

/**
 * This class is an implementation of the TicketDAO interface for performing CRUD operations on Route objects in the database.
 * <p>
 * It extends the GenericDAO class and overrides its methods to perform Ticket-specific database queries.
 */
public class TicketDAOImpl extends GenericDAO<Ticket> implements TicketDAO {
    private static final String INSERT_TICKET = "INSERT INTO ticket (passengers_count, price, user_id, ship_id, document_path) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_TICKET = "UPDATE ticket SET status_id = ? WHERE id = ?";
    private static final String UPDATE_TICKET_STATUS = "UPDATE ticket SET status_id = ? WHERE id = ?";
    private static final String DELETE_TICKET = "DELETE FROM ticket WHERE id = ?";
    private static final String SELECT_ALL = "SELECT ticket.id, ticket.passengers_count, ticket.price, ticket.status_id, document_path, " +
            "user.id, user.first_name, user.last_name, user.balance, ticket.ship_id, ship.name, route.id, route.name, " +
            "route.start_of_cruise, route.end_of_cruise FROM ticket LEFT JOIN user on user.id = ticket.user_id LEFT JOIN ship on " +
            "ship.id = ticket.ship_id LEFT JOIN route on route.id = ship.route_id";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE ticket.id = ?";
    private static final String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM ticket LEFT JOIN user ON user.id = ticket.user_id";

    /**
     * Inserts a new Ticket object into the database.
     *
     * @param ticket the Ticket object to insert
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void create(Ticket ticket) throws DAOException {
        executeNoReturn(INSERT_TICKET, ticket.getPassengersCount(),
                ticket.getPrice(),
                ticket.getUser().getId(),
                ticket.getShip().getId(),
                ticket.getDocumentPath());
    }

    /**
     * Updates a Ticket object in the database.
     *
     * @param ticket the Ticket object to update
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void update(Ticket ticket) throws DAOException {
        executeNoReturn(UPDATE_TICKET, ticket.getStatus().getIndex(),
                ticket.getId());
    }

    /**
     * Updates the status of a Ticket object with the specified ID.
     *
     * @param ticketId the ID of the ticket to update
     * @param status   the new status of the ticket
     * @throws DAOException if there is an error updating the ticket status
     */
    @Override
    public void updateTicketStatus(long ticketId, Status status) throws DAOException {
        executeNoReturn(UPDATE_TICKET_STATUS, status.getIndex(), ticketId);
    }

    /**
     * Deletes a Ticket object from the database.
     *
     * @param id the id of the Ticket object to delete
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_TICKET, id);
    }

    /**
     * Retrieves a Ticket object from the database by its id.
     *
     * @param id the id of the Ticket object to retrieve
     * @return an Optional containing the Ticket object, or an empty Optional if it does not exist
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public Optional<Ticket> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    /**
     * Retrieves all Ticket objects from the database.
     *
     * @return a List of all Ticket objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Ticket> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    /**
     * Retrieves a List of sorted Ticket objects from the database using a query.
     *
     * @param query the SQL query to use for sorting
     * @return a List of sorted Ticket objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Ticket> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }

    /**
     * Retrieves the number of rows in the Ticket table that match a query.
     *
     * @param query the SQL query to use for counting rows
     * @return the number of rows in the Ticket table that match the query
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    /**
     * Maps a ResultSet object to a Ticket entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Ticket entity object
     * @throws SQLException if a database access error occurs
     */
    @Override
    protected Ticket mapToEntity(ResultSet rs) throws SQLException {
        return Ticket.builder()
                .id(rs.getInt(TICKET_ID))
                .passengersCount(rs.getInt(TICKET_PASSENGERS_COUNT))
                .price(rs.getBigDecimal(TICKET_PRICE))
                .status(Status.getStatus(rs.getInt(TICKET_STATUS_ID)))
                .documentPath(rs.getString(TICKET_DOCUMENT_PATH))
                .user(mapToUser(rs))
                .ship(mapToShip(rs))
                .build();
    }

    /**
     * Maps a ResultSet object to a User entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped User entity object
     * @throws SQLException if a database access error occurs
     */
    private User mapToUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getInt(USER_ID))
                .firstName(rs.getString(USER_FIRST_NAME))
                .lastName(rs.getString(USER_LAST_NAME))
                .balance(rs.getBigDecimal(USER_BALANCE))
                .build();
    }

    /**
     * Maps a ResultSet object to a Ship entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Ship entity object
     * @throws SQLException if a database access error occurs
     */
    private Ship mapToShip(ResultSet rs) throws SQLException {
        return Ship.builder()
                .id(rs.getInt(TICKET_SHIP_ID))
                .name(rs.getString(SHIP_NAME))
                .route(mapToRoute(rs))
                .build();
    }

    /**
     * Maps a ResultSet object to a Route entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Route entity object
     * @throws SQLException if a database access error occurs
     */
    private Route mapToRoute(ResultSet rs) throws SQLException {
        return Route.builder()
                .id(rs.getInt(ROUTE_ID))
                .name(rs.getString(ROUTE_NAME))
                .startOfCruise(rs.getDate(ROUTE_START_OF_CRUISE).toLocalDate())
                .endOfCruise(rs.getDate(ROUTE_END_OF_CRUISE).toLocalDate())
                .build();
    }
}

package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.TicketDAO;
import com.java.cruisecompany.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TicketDAOImpl extends GenericDAO<Ticket> implements TicketDAO {
    private static final String INSERT_TICKET = "INSERT INTO ticket (passengers_count, price, user_id, ship_id) " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE_TICKET = "UPDATE ticket SET status_id = ? WHERE id = ?";
    private static final String UPDATE_TICKET_STATUS = "UPDATE ticket SET status_id = ? WHERE id = ?";
    private static final String DELETE_TICKET = "DELETE FROM ticket WHERE id = ?";
    private static final String SELECT_ALL = "SELECT ticket.id, ticket.passengers_count, ticket.price, ticket.status_id, " +
            "user.id, user.first_name, user.last_name, user.balance, ticket.ship_id, ship.name, r.id, r.name, " +
            "r.start_of_cruise FROM ticket LEFT JOIN user on user.id = ticket.user_id LEFT JOIN ship on " +
            "ship.id = ticket.ship_id LEFT JOIN route r on r.id = ship.route_id";
    private static final String SELECT_TICKETS_BY_USER = SELECT_ALL + " WHERE user.id = ?";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE ticket.id = ?";
    private static final String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM ticket LEFT JOIN user ON user.id = ticket.user_id";
    @Override
    public void create(Ticket entity) throws DAOException {
        executeNoReturn(INSERT_TICKET, entity.getPassengersCount(),
                entity.getPrice(),
                entity.getUser().getId(),
                entity.getShip().getId());
    }

    @Override
    public void update(Ticket entity) throws DAOException {
        executeNoReturn(UPDATE_TICKET, entity.getStatus().getIndex(),
                entity.getId());
    }

    @Override
    public void updateTicketStatus(long ticketId, Status status) throws DAOException {
        executeNoReturn(UPDATE_TICKET_STATUS, status.getIndex(), ticketId);
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_TICKET, id);
    }

    @Override
    public Optional<Ticket> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<Ticket> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    public List<Ticket> findSorted(String query) throws DAOException {
        System.out.println(SELECT_ALL + query);
        return executeListReturn(SELECT_ALL + query);
    }

    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    @Override
    protected Ticket mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return Ticket.builder()
                .id(rs.getInt(++k))
                .passengersCount(rs.getInt(++k))
                .price(rs.getBigDecimal(++k))
                .status(Status.getStatus(rs.getInt(++k)))
                .user(mapToUser(rs, k))
                .ship(mapToShip(rs, 8))
                .build();
    }

    private User mapToUser(ResultSet rs, int k) throws SQLException {
        return User.builder()
                .id(rs.getInt(++k))
                .firstName(rs.getString(++k))
                .lastName(rs.getString(++k))
                .balance(rs.getBigDecimal(++k))
                .build();
    }

    private Ship mapToShip(ResultSet rs, int k) throws SQLException {
        return Ship.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .route(mapToRoute(rs, k))
                .build();
    }

    private Route mapToRoute(ResultSet rs, int k) throws SQLException {
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise(rs.getDate(++k).toLocalDate())
                .build();
    }

}

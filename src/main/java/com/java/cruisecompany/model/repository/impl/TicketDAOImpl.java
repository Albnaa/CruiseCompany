package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.entity.User;
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
    private static final String DELETE_TICKET = "DELETE FROM ticket WHERE id = ?";
    private static final String FIND_ALL = "SELECT ticket.id, ticket.passengers_count, ticket.price, ticket.status_id, user.id, user.login, user.email," +
            " user.first_name, user.last_name, ticket.ship_id, ship.name FROM ticket LEFT JOIN user on user.id = ticket.user_id" +
            " LEFT JOIN ship on ship.id = ticket.ship_id";
    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";
    @Override
    public void create(Ticket entity) throws DAOException {
        executeNoReturn(INSERT_TICKET, entity.getPassengers_count(),
                entity.getPrice(),
                entity.getUser().getId(),
                entity.getShip().getId());
    }

    @Override
    public void update(Ticket entity) throws DAOException {
        executeNoReturn(UPDATE_TICKET, entity.getStatusId(),
                entity.getId());
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_TICKET, id);
    }

    @Override
    public Optional<Ticket> findById(long id) throws DAOException {
        return executeOneReturn(FIND_BY_ID, id);
    }

    @Override
    public List<Ticket> findAll() throws DAOException {
        return executeListReturn(FIND_ALL);
    }

    @Override
    public List<Ticket> findSorted(String query) throws DAOException {
        return null;
    }

    @Override
    public long getNumOfRows(String query) throws DAOException {
        return 0;
    }

    @Override
    protected Ticket mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return Ticket.builder()
                .id(rs.getInt(++k))
                .passengers_count(rs.getInt(++k))
                .price(rs.getDouble(++k))
                .statusId(rs.getInt(++k))
                .user(mapToUser(rs))
                .ship(mapToShip(rs))
                .build();
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        int k = 4;
        return User.builder()
                .id(rs.getInt(++k))
                .login(rs.getString(++k))
                .email(rs.getString(++k))
                .firstName(rs.getString(++k))
                .lastName(rs.getString(++k))
                .build();
    }

    private Ship mapToShip(ResultSet rs) throws SQLException {
        int k = 9;
        return Ship.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .build();
    }
}

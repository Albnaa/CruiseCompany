package com.app.webapplication.model.repository.impl;

import com.app.webapplication.model.entity.Ship;
import com.app.webapplication.model.entity.User;
import com.app.webapplication.model.repository.GenericDao;
import com.app.webapplication.model.repository.TicketDao;
import com.app.webapplication.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl extends GenericDao<Ticket> implements TicketDao {
    private static final String INSERT_TICKET = "";
    private static final String UPDATE_TICKET = "UPDATE ticket SET status_id = ? WHERE id = ?";
    private static final String DELETE_TICKET = "DELETE FROM ticket WHERE id = ?";
    private static final String FIND_ALL = "SELECT ticket.id, ticket.passengers_count, ticket.price, ticket.status_id, user.id, user.login, user.email,\n" +
            "       user.first_name, user.last_name, ticket.ship_id, ship.name FROM ticket LEFT JOIN user on user.id = ticket.user_id\n" +
            "       LEFT JOIN ship on ship.id = ticket.ship_id";
    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";
    @Override
    public void create(Ticket entity) {
        executeNoReturn(INSERT_TICKET);
    }

    @Override
    public void update(Ticket entity) {
        executeNoReturn(UPDATE_TICKET, entity.getStatusId(),
                entity.getId());
    }

    @Override
    public void delete(Ticket entity) {
        executeNoReturn(DELETE_TICKET, entity.getId());
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return executeOneReturn(FIND_BY_ID, id);
    }

    @Override
    public List<Ticket> findAll() {
        return executeListReturn(FIND_ALL);
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
                .first_name(rs.getString(++k))
                .last_name(rs.getString(++k))
                .build();
    }

    private Ship mapToShip(ResultSet rs) throws SQLException {
        int k = 9;
        return Ship.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .build();
    }

    public static void main(String[] args) {
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        System.out.println(ticketDao.findAll());
    }
}

package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.ShipDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShipDAOImpl extends GenericDAO<Ship> implements ShipDAO {

    private static final String INSERT_SHIP = "INSERT INTO ship (name, capacity, visited_ports, staff) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SHIP = "UPDATE ship SET name = ?, capacity = ?, visited_ports = ?, staff = ?, " +
            "route_id = ?";
    private static final String DELETE_SHIP = "DELETE FROM ship WHERE id = ?";
    private static final String FIND_ALL = "SELECT ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff," +
            " route.id, route.name, route.start_of_cruise, route.end_of_cruise, route.price FROM ship LEFT JOIN route ON" +
            " ship.route_id = route.id";
    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";
    @Override
    public void create(Ship entity) throws DAOException {
        executeNoReturn(INSERT_SHIP, entity.getName(),
                entity.getCapacity(),
                entity.getVisited_ports(),
                entity.getStaff(),
                entity.getRoute().getId());
    }

    @Override
    public void update(Ship entity) throws DAOException {
        executeNoReturn(UPDATE_SHIP, entity.getName(),
                entity.getCapacity(),
                entity.getVisited_ports(),
                entity.getStaff(),
                entity.getRoute().getId());
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_SHIP, id);
    }

    @Override
    public Optional<Ship> findById(int id) throws DAOException {
        return executeOneReturn(FIND_BY_ID, id);
    }

    @Override
    public List<Ship> findAll() throws DAOException {
        return executeListReturn(FIND_ALL);
    }

    @Override
    public List<Ship> findSorted(String query) throws DAOException {
        return null;
    }

    @Override
    public long getNumOfRows(String query) throws DAOException {
        return 0;
    }

    @Override
    protected Ship mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return Ship.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .capacity(rs.getInt(++k))
                .visited_ports(rs.getInt(++k))
                .staff(rs.getInt(++k))
                .route(mapToRoute(rs, k)) //rework
                .build();
    }

    private Route mapToRoute(ResultSet rs, int k) throws SQLException {
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise(rs.getDate(++k).toLocalDate())
                .endOfCruise(rs.getDate(++k).toLocalDate())
                .price(rs.getBigDecimal(++k))
                .build();
    }
}

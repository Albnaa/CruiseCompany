package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.ShipDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShipDAOImpl extends GenericDAO<Ship> implements ShipDAO {

    private static final String INSERT_SHIP = "INSERT INTO ship (name, capacity, visited_ports, staff) VALUES (?, ?, ?, ?)";
    private static final String INSERT_ROUTE = "UPDATE ship SET route_id = ? WHERE id = ?";
    private static final String UPDATE_SHIP = "UPDATE ship SET name = ?, capacity = ?, visited_ports = ?, staff = ? WHERE id = ?";
    private static final String DELETE_SHIP = "DELETE FROM ship WHERE id = ?";
    private static final String DELETE_ROUTE = "UPDATE ship SET route_id = null WHERE id = ?";
    private static final String SELECT_ALL = "SELECT ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff," +
            " route.id, route.name, route.start_of_cruise, route.end_of_cruise, route.price FROM ship LEFT JOIN route ON" +
            " ship.route_id = route.id";

    private static final String SELECT_ALL_WITH_ROUTES = "SELECT ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff," +
            " route.id, route.name, route.start_of_cruise, route.end_of_cruise, route.price FROM ship INNER JOIN route ON" +
            " ship.route_id = route.id";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE ship.id = ?";
    private static final String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM ship LEFT JOIN route ON ship.route_id = route.id";

    @Override
    public void create(Ship entity) throws DAOException {
        executeNoReturn(INSERT_SHIP, entity.getName(),
                entity.getCapacity(),
                entity.getVisited_ports(),
                entity.getStaff());
    }

    @Override
    public void update(Ship entity) throws DAOException {
        executeNoReturn(UPDATE_SHIP, entity.getName(),
                entity.getCapacity(),
                entity.getVisited_ports(),
                entity.getStaff(),
                entity.getId());
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_SHIP, id);
    }

    @Override
    public Optional<Ship> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<Ship> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    public List<Ship> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }

    @Override
    public List<Ship> findSortedWithRoutes(String query) throws DAOException {
        return executeListReturn(SELECT_ALL_WITH_ROUTES + query);
    }

    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    @Override
    public void addRoute(long shipId, long routeId) throws DAOException {
        executeNoReturn(INSERT_ROUTE, routeId, shipId);
    }

    @Override
    public void deleteRoute(long shipId) throws DAOException {
        executeNoReturn(DELETE_ROUTE, shipId);
    }

    @Override
    protected Ship mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        if (rs.getString("route.name") != null) {
            return Ship.builder()
                    .id(rs.getInt(++k))
                    .name(rs.getString(++k))
                    .capacity(rs.getInt(++k))
                    .visited_ports(rs.getInt(++k))
                    .staff(rs.getInt(++k))
                    .route(mapToRoute(rs, k))
                    .build();
        } else {
            return Ship.builder()
                    .id(rs.getInt(++k))
                    .name(rs.getString(++k))
                    .capacity(rs.getInt(++k))
                    .visited_ports(rs.getInt(++k))
                    .staff(rs.getInt(++k))
                    .build();
        }
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

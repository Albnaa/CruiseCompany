package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.connectionpool.DBManager;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.RouteDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class RouteDAOImpl extends GenericDAO<Route> implements RouteDAO {
    private final static String INSERT_ROUTE = "INSERT INTO route (name, start_of_cruise, end_of_cruise, price) VALUES (?, ?, ?, ?)";
    private final static String INSERT_WAYPOINT = "INSERT INTO route_has_port (route_id, port_id, arrive_time, departure_time) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_ROUTE = "UPDATE route SET name = ?, start_of_cruise = ?, end_of_cruise = ?, price = ? WHERE id = ?";
    private final static String DELETE_ROUTE = "DELETE FROM route WHERE id = ?";
    private final static String DELETE_WAYPOINT = "DELETE FROM route_has_port WHERE route_id = ? AND port_id = ?";
    private final static String SELECT_ALL = "SELECT * FROM route";
    private final static String SELECT_ALL_JOIN = "SELECT r.id, r.name, r.start_of_cruise, r.end_of_cruise, p.id, p.name, " +
            "rhp.arrive_time, rhp.departure_time FROM route r INNER JOIN route_has_port rhp on r.id = rhp.route_id " +
            "LEFT JOIN port p on p.id = rhp.port_id";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE route.id = ?";

    private final static String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) from route";

    private final static String SELECT_ALL_ROUTE_WAYPOINTS = "SELECT p.id, p.name, rhp.arrive_time, rhp.departure_time " +
            "FROM route r LEFT JOIN route_has_port rhp on r.id = rhp.route_id INNER JOIN port p on p.id = rhp.port_id " +
            "WHERE r.id = ?";

    @Override
    public void create(Route entity) throws DAOException {
        executeNoReturn(INSERT_ROUTE, entity.getName(),
                entity.getStartOfCruise(),
                entity.getEndOfCruise(),
                entity.getPrice());
    }

    @Override
    public void update(Route entity) throws DAOException {
        executeNoReturn(UPDATE_ROUTE, entity.getName(),
                entity.getStartOfCruise(),
                entity.getEndOfCruise(),
                entity.getPrice(),
                entity.getId());
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_ROUTE, id);
    }

    @Override
    public Optional<Route> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<Route> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    public List<Route> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }

    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    @Override
    public void deleteWaypoint(long routeId, long portId) throws DAOException {
        executeNoReturn(DELETE_WAYPOINT, routeId, portId);
    }

    @Override
    public void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws DAOException {
        executeNoReturn(INSERT_WAYPOINT, routeId, portId, arriveTime, departureTime);
    }

    @Override
    public List<Waypoint> getRouteWaypoints(long id) {
        List<Waypoint> list = new ArrayList<>();
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_ROUTE_WAYPOINTS)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    int k = 0;

                    Port port = Port.builder()
                            .id(rs.getInt(++k))
                            .name(rs.getString(++k))
                            .build();

                    list.add(Waypoint.builder()
                            .port(port)
                            .arriveTime(rs.getDate(++k).toLocalDate())
                            .departureTime(rs.getDate(++k).toLocalDate())
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected Route mapToEntity(ResultSet rs) throws SQLException {

        int k = 0;
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise(rs.getDate(++k).toLocalDate())
                .endOfCruise(rs.getDate(++k).toLocalDate())
                .price(rs.getBigDecimal(++k))
                .build();
    }

}

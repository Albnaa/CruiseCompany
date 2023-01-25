package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.connectionpool.DBManager;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.RouteDAO;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RouteDAOImpl extends GenericDAO<Route> implements RouteDAO {
    private final static String INSERT_ROUTE = "INSERT INTO route (name, start_of_cruise, end_of_cruise) VALUES (?, ?, ?)";
    private final static String UPDATE_ROUTE = "UPDATE route SET name = ?, start_of_cruise = ?, end_of_cruise = ? WHERE id = ?";
    private final static String DELETE_ROUTE = "DELETE FROM route WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM route";
    private final static String SELECT_ALL_JOIN = "SELECT r.id, r.name, r.start_of_cruise, r.end_of_cruise, p.id, p.name, " +
            "rhp.arrive_time, rhp.departure_time FROM route r INNER JOIN route_has_port rhp on r.id = rhp.route_id " +
            "LEFT JOIN port p on p.id = rhp.port_id";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE r.id = ?";

    private final static String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) from route";

    @Override
    public void create(Route entity) throws DAOException {
        executeNoReturn(INSERT_ROUTE, entity.getName(),
                entity.getStartOfCruise(),
                entity.getEndOfCruise());
    }

    @Override
    public void update(Route entity) throws DAOException {
        executeNoReturn(UPDATE_ROUTE, entity.getName(),
                entity.getStartOfCruise(),
                entity.getEndOfCruise(),
                entity.getId());
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_ROUTE, id);
    }

    @Override
    public Optional<Route> findById(int id) throws DAOException {
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

//    public List<Route> getListWithPorts(String query) throws DAOException {
//        int lastRouteId = -1;
//        Route currRoute = null;
//        List<Route> routes = new ArrayList<>();
//        try (Connection con = DBManager.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query)) {
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    int routeId = rs.getInt("r.id");
//
//                    if (routeId != lastRouteId) {
//                        currRoute = Route.builder()
//                                .id(routeId)
//                                .name(rs.getString("r.name"))
//                                .startOfCruise(rs.getDate("r.start_of_cruise").toLocalDate())
//                                .endOfCruise(rs.getDate("r.end_of_cruise").toLocalDate())
//                                .waypoints(new LinkedList<>())
//                                .build();
//                        routes.add(currRoute);
//                    }
//
//                    Port port = Port.builder()
//                            .id(rs.getInt("p.id"))
//                            .name(rs.getString("p.name"))
//                            .build();
//
//                    Waypoint waypoint = Waypoint.builder()
//                            .port(port)
//                            .arriveTime(rs.getDate("rhp.arrive_time").toLocalDate())
//                            .departureTime(rs.getDate("rhp.departure_time").toLocalDate())
//                            .build();
//
//                    assert currRoute != null;
//                    currRoute.getWaypoints().add(waypoint);
//                    lastRouteId = routeId;
//                }
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//        return routes;
//    }

    @Override
    protected Route mapToEntity(ResultSet rs) throws SQLException {

        int k = 0;
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise(rs.getDate(++k).toLocalDate())
                .endOfCruise(rs.getDate(++k).toLocalDate())
                .build();
    }

}

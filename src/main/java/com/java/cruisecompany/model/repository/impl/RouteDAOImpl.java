package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.connectionpool.DBManager;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.RouteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class RouteDAOImpl extends GenericDAO<Route> implements RouteDAO {
    private final static String INSERT_ROUTE = "INSERT INTO route (name, start_of_cruise, end_of_cruise) VALUES (?, ?, ?)";
    private final static String UPDATE_ROUTE = "UPDATE route SET name = ?, start_of_cruise = ?, end_of_cruise = ? WHERE id = ?";
    private final static String DELETE_ROUTE = "DELETE FROM route WHERE id = ?";
    private final static String SELECT_ALL = "SELECT r.id, r.name, r.start_of_cruise, r.end_of_cruise, p.id, p.name " +
            "FROM route r LEFT JOIN route_has_port rhp on r.id = rhp.route_id LEFT JOIN port p on p.id = rhp.port_id";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE r.id = ?";

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
        return null;
    }

    @Override
    public long getNumOfRows(String query) throws DAOException {
        return 0;
    }

    public List<Route> getListWithPorts(String query) throws DAOException {
        int lastRouteId = -1;
        Route currRoute = null;
        List<Route> routes = new ArrayList<>();
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int routeId = rs.getInt(1);
                    String cruiseName = rs.getString(2);
                    Date startDate = rs.getDate(3);
                    Date endDate = rs.getDate(4);
                    int portId = rs.getInt(5);
                    String portName = rs.getString(6);

                    if (routeId != lastRouteId) {
                        currRoute = Route.builder()
                                .id(routeId)
                                .name(cruiseName)
                                .startOfCruise(startDate)
                                .endOfCruise(endDate)
                                .ports(new HashSet<Port>())
                                .build();
                        routes.add(currRoute);
                    }

                    Port port = Port.builder()
                            .id(portId)
                            .name(portName)
                            .build();

                    currRoute.getPorts().add(port);
                    lastRouteId = routeId;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    protected Route mapToEntity(ResultSet rs) throws SQLException {

        int k = 0;
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise((Date) rs.getObject(++k))
                .endOfCruise((Date) rs.getObject(++k))
                .build();
    }

    public static void main(String[] args) throws DAOException {
        RouteDAOImpl routeDAO = new RouteDAOImpl();
        System.out.println(routeDAO.getListWithPorts("SELECT r.id, r.name, r.start_of_cruise, r.end_of_cruise, p.id, p.name FROM route r LEFT JOIN route_has_port rhp on r.id = rhp.route_id LEFT JOIN port p on p.id = rhp.port_id"));
    }
}

package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.RouteDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected Route mapToEntity(ResultSet rs) throws SQLException {

        int k = 0;
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise((Date) rs.getObject(++k))
                .endOfCruise((Date) rs.getObject(++k))
                .build();
    }
}

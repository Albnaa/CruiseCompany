package com.app.webapplication.model.repository.impl;

import com.app.webapplication.model.repository.GenericDao;
import com.app.webapplication.model.repository.RouteDao;
import com.app.webapplication.model.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RouteDaoImpl extends GenericDao<Route> implements RouteDao {
    private final static String INSERT_ROUTE = "INSERT INTO route (name, start_of_cruise, end_of_cruise) VALUES (?, ?, ?)";
    private final static String UPDATE_ROUTE = "UPDATE route SET name = ?, start_of_cruise = ?, end_of_cruise = ? WHERE id = ?";
    private final static String DELETE_ROUTE = "DELETE FROM route WHERE id = ?";
    private final static String SELECT_ALL = "SELECT r.id, r.name, r.start_of_cruise, r.end_of_cruise, p.id, p.name " +
            "FROM route r LEFT JOIN route_has_port rhp on r.id = rhp.route_id LEFT JOIN port p on p.id = rhp.port_id";
    private final static String SELECT_BY_ID = SELECT_ALL + "WHERE r.id = ?";

    @Override
    public void create(Route entity) {
        executeNoReturn(INSERT_ROUTE, entity.getName(),
                entity.getStartOfCruise(),
                entity.getEndOfCruise());
    }

    @Override
    public void update(Route entity) {
        executeNoReturn(UPDATE_ROUTE, entity.getName(),
                entity.getStartOfCruise(),
                entity.getEndOfCruise(),
                entity.getId());
    }

    @Override
    public void delete(Route entity) {
        executeNoReturn(DELETE_ROUTE, entity.getId());
    }

    @Override
    public Optional<Route> findById(int id) {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<Route> findAll() {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    protected Route mapToEntity(ResultSet rs) throws SQLException {

        int k = 0;
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise((LocalDateTime) rs.getObject(++k))
                .endOfCruise((LocalDateTime) rs.getObject(++k))
                .build();
    }
}

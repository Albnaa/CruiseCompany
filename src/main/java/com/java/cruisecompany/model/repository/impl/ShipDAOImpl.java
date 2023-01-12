package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.ShipDAO;
import com.java.cruisecompany.model.entity.Ship;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ShipDAOImpl extends GenericDAO<Ship> implements ShipDAO {

    private static final String INSERT_SHIP = "INSERT INTO ship (name, capacity, visited_ports, staff, route_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SHIP = "UPDATE ship SET name = ?, capacity = ?, visited_ports = ?, staff = ?, " +
            "route_id = ?";
    private static final String DELETE_SHIP = "DELETE FROM ship WHERE id = ?";
    private static final String FIND_ALL = "SELECT ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff," +
            " route.id, route.name, route.start_of_cruise, route.end_of_cruise FROM ship LEFT JOIN route ON" +
            " ship.route_id = route.id";
    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";
    @Override
    public void create(Ship entity) {
        executeNoReturn(INSERT_SHIP, entity.getName(),
                entity.getCapacity(),
                entity.getVisited_ports(),
                entity.getStaff(),
                entity.getRoute().getId());
    }

    @Override
    public void update(Ship entity) {
        executeNoReturn(UPDATE_SHIP, entity.getName(),
                entity.getCapacity(),
                entity.getVisited_ports(),
                entity.getStaff(),
                entity.getRoute().getId());
    }

    @Override
    public void delete(Ship entity) {
        executeNoReturn(DELETE_SHIP, entity.getId());
    }

    @Override
    public Optional<Ship> findById(int id) {
        return executeOneReturn(FIND_BY_ID, id);
    }

    @Override
    public List<Ship> findAll() {
        return executeListReturn(FIND_ALL);
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
                .route(mapToRoute(rs)) //rework
                .build();
    }

    private Route mapToRoute(ResultSet rs) throws SQLException {
        int k = 5;
        return Route.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .startOfCruise((Date) rs.getObject(++k))
                .endOfCruise((Date) rs.getObject(++k))
                .build();
    }

    public static void main(String[] args) {
        ShipDAO shipDAO = new ShipDAOImpl();
        System.out.println(shipDAO.findAll());
    }
}

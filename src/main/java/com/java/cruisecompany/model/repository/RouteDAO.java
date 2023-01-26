package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;

import java.time.LocalDate;
import java.util.List;

public interface RouteDAO extends EntityDAO<Route> {
    void deleteWaypoint(long routeId, long portId) throws DAOException;
    void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws DAOException;

    List<Waypoint> getRouteWaypoints(long id);
}

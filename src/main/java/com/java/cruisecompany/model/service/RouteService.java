package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;

import java.time.LocalDate;

/**
 * The RouteService interface provides methods to manage the business logic related to routes.
 * <p>
 * It extends the Service interface and provides additional methods specific to route entity.
 */
public interface RouteService extends Service<RouteDTO> {
    /**
     * Adds a new waypoint to a route with the specified port, arrival time and departure time.
     *
     * @param routeId the ID of the route to add the waypoint to
     * @param portId the ID of the port to add as a waypoint
     * @param arriveTime the arrival time at the port
     * @param departureTime the departure time from the port
     * @throws ServiceException if there is an error while adding the waypoint
     */
    void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws ServiceException;

    /**
     * Deletes a waypoint from a route with the specified port.
     *
     * @param routeId the ID of the route to remove the waypoint from
     * @param portId  the ID of the port to remove as a waypoint
     * @throws ServiceException if there is an error while deleting the waypoint
     */
    void deleteWaypoint(long routeId, long portId) throws ServiceException;
}

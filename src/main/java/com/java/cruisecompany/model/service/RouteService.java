package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;

import java.time.LocalDate;

public interface RouteService extends Service<RouteDTO> {
    void deleteWaypoint(long routeId, long portId) throws ServiceException;
    void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws ServiceException;
}

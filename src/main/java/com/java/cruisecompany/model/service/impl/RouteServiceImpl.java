package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchRouteException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import com.java.cruisecompany.model.repository.RouteDAO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.MapperDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoRoute;
import static com.java.cruisecompany.model.utils.MapperDTO.mapRouteToDTO;

public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;

    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @Override
    public void create(RouteDTO routeDTO) throws ServiceException{
        try {
            routeDAO.create(mapDTOtoRoute(routeDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(RouteDTO routeDTO) throws ServiceException {
        try {
            routeDAO.update(mapDTOtoRoute(routeDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            routeDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    @Override
    public Optional<RouteDTO> findById(long id) throws ServiceException {
        RouteDTO routeDTO;
        try {
            Route route = routeDAO.findById(id).orElseThrow(NoSuchRouteException::new);
            List<Waypoint> waypoints = routeDAO.getRouteWaypoints(id);
            waypoints.sort(Comparator.comparing(Waypoint::getArriveTime));
            route.setWaypoints(waypoints);

            routeDTO = mapRouteToDTO(route);
            routeDTO.setNumOfPorts(route.getWaypoints().size());
        } catch (DAOException e) {
            throw new ServiceException();
        }
        return Optional.of(routeDTO);
    }

    @Override
    public List<RouteDTO> findAll() {
        return null;
    }

    @Override
    public List<RouteDTO> findSorted(String query) throws ServiceException {
        List<RouteDTO> routeDTOs;
        try {
            routeDTOs = routeDAO.findSorted(query).stream()
                    .peek(route -> route.setWaypoints(routeDAO.getRouteWaypoints(route.getId())))
                    .map(MapperDTO::mapRouteToDTO)
                    .peek(route -> route.setNumOfPorts(route.getWaypoints().size()))
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routeDTOs;
    }

    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return routeDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void deleteWaypoint(long routeId, long portId) throws ServiceException {
        try {
            routeDAO.deleteWaypoint(routeId, portId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws ServiceException {
        try {
            routeDAO.addWaypoint(routeId, portId, arriveTime, departureTime);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

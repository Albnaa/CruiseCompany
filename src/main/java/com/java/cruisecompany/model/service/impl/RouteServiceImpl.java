package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.repository.RouteDAO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.MapperDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.utils.MapperDTO.mapRouteToDTO;

public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;

    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @Override
    public void create(RouteDTO entity) {

    }

    @Override
    public void update(RouteDTO entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<RouteDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<RouteDTO> findAll() {
        return null;
    }

    @Override
    public List<RouteDTO> findSorted(String query) throws ServiceException {
        List<RouteDTO> routeDTOs = new ArrayList<>();
        try {
        List<Route> routes = routeDAO.findSorted(query);
        routes.forEach(route -> routeDTOs.add(mapRouteToDTO(route)));
        routeDTOs.forEach(routeDTO -> routeDTO.setDuration(routeDTO.getWaypoints().size()));
        routeDTOs.forEach(routeDTO -> routeDTO.setNumOfPorts(routeDTO.getWaypoints().size()));
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
}

package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.repository.RouteDAO;
import com.java.cruisecompany.model.service.RouteService;

import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;

    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @Override
    public void create(Route entity) {

    }

    @Override
    public void update(Route entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<Route> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Route> findAll() {
        return null;
    }
}

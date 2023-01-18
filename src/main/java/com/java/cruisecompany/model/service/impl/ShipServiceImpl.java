package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.repository.ShipDAO;
import com.java.cruisecompany.model.service.ShipService;

import java.util.List;
import java.util.Optional;

public class ShipServiceImpl implements ShipService {
    private final ShipDAO shipDAOl;

    public ShipServiceImpl(ShipDAO shipDAOl) {
        this.shipDAOl = shipDAOl;
    }

    @Override
    public void create(Ship entity) {

    }

    @Override
    public void update(Ship entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<Ship> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Ship> findAll() {
        return null;
    }
}

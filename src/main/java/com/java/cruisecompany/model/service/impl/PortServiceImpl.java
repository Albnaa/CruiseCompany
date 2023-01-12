package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.repository.PortDAO;
import com.java.cruisecompany.model.service.PortService;

import java.util.List;
import java.util.Optional;

public class PortServiceImpl implements PortService { //add validation and dto object

    private final PortDAO portDAO;

    public PortServiceImpl(PortDAO portDAO) {
        this.portDAO = portDAO;
    }

    @Override
    public void create(Port entity) {
        portDAO.create(entity);
    }

    @Override
    public void update(Port entity) {
        portDAO.update(entity);
    }

    @Override
    public void delete(Port entity) {
        portDAO.delete(entity);
    }

    @Override
    public Optional<Port> findById(int id) {
        return portDAO.findById(id);
    }

    @Override
    public List<Port> findAll() {
        return portDAO.findAll();
    }
}

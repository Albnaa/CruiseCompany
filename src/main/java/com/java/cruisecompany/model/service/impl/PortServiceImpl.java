package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.ServiceException;
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
    public void create(Port entity) throws ServiceException {
        try {
        portDAO.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Port entity) throws ServiceException {
        try {
        portDAO.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
        portDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Port> findById(int id) throws ServiceException {
        try {
        return portDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Port> findAll() throws ServiceException {
        try {
        return portDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

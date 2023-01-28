package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchPortException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.repository.PortDAO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoPort;
import static com.java.cruisecompany.model.utils.MapperDTO.mapPortToDTO;

public class PortServiceImpl implements PortService { //add validation and dto object

    private final PortDAO portDAO;

    public PortServiceImpl(PortDAO portDAO) {
        this.portDAO = portDAO;
    }

    @Override
    public void create(PortDTO entity) throws ServiceException {
        ValidationUtil.validateOnlyLettersWithSpaces(entity.getName(), "error.ports.name");
        Port port = mapDTOtoPort(entity);
        try {
            portDAO.create(port);
        } catch (DAOException e) {
            checkAlreadyExist(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(PortDTO entity) throws ServiceException {
        ValidationUtil.validateOnlyLettersWithSpaces(entity.getName(), "error.ports.name");
        Port port = mapDTOtoPort(entity);
        try {
            portDAO.update(port);
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
    public Optional<PortDTO> findById(long id) throws ServiceException {
        Optional<PortDTO> portDTO;
        try {
            Optional<Port> port = portDAO.findById(id);
            portDTO = Optional.of(mapPortToDTO(port.orElseThrow(NoSuchPortException::new)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return portDTO;
    }

    @Override
    public List<PortDTO> findAll() throws ServiceException {
        List<PortDTO> portDTOs = new ArrayList<>();
        try {
            List<Port> ports = portDAO.findAll();
            ports.forEach(port -> portDTOs.add(mapPortToDTO(port)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return portDTOs;
    }

    @Override
    public List<PortDTO> findSorted(String query) throws ServiceException {
        List<PortDTO> portDTOs = new ArrayList<>();
        try {
            List<Port> ports = portDAO.findSorted(query);
            ports.forEach(port -> portDTOs.add(mapPortToDTO(port)));
        } catch (DAOException e) {
            throw new ServiceException();
        }
        return portDTOs;
    }

    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return portDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private static void checkAlreadyExist(DAOException e) throws InvalidInputException {
        String message = e.getMessage();
        if (message.contains("Duplicate entry")) {
            throw new InvalidInputException("error.ports.nameExists", e);
        }
    }
}

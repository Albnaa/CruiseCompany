package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchPortException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.dao.PortDAO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.MapperDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoPort;
import static com.java.cruisecompany.model.utils.MapperDTO.mapPortToDTO;

@Log4j2
public class PortServiceImpl implements PortService {

    private final PortDAO portDAO;

    public PortServiceImpl(PortDAO portDAO) {
        this.portDAO = portDAO;
    }

    @Override
    public void create(PortDTO portDTO) throws ServiceException {
        try {
            portDAO.create(mapDTOtoPort(portDTO));
        } catch (DAOException e) {
            log.error("Port service create error " + e.getMessage());
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(PortDTO portDTO) throws ServiceException {
        try {
            portDAO.update(mapDTOtoPort(portDTO));
        } catch (DAOException e) {
            log.error("Port service update error " + e.getMessage());
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            portDAO.delete(id);
        } catch (DAOException e) {
            log.error("Port service delete error " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<PortDTO> findById(long id) throws ServiceException {
        PortDTO portDTO;
        try {
            portDTO = mapPortToDTO(portDAO.findById(id).orElseThrow(NoSuchPortException::new));
        } catch (DAOException e) {
            log.error("Port service findById error " + e.getMessage());
            throw new ServiceException(e);
        }
        return Optional.of(portDTO);
    }

    @Override
    public List<PortDTO> findAll() throws ServiceException {
        List<PortDTO> portDTOs;
        try {
            portDTOs = portDAO.findAll().stream()
                    .map(MapperDTO::mapPortToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            log.error("Port service findAll error " + e.getMessage());
            throw new ServiceException(e);
        }
        return portDTOs;
    }

    @Override
    public List<PortDTO> findSorted(String query) throws ServiceException {
        List<PortDTO> portDTOs;
        try {
            portDTOs = portDAO.findSorted(query).stream()
                    .map(MapperDTO::mapPortToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            log.error("Port service findSorted error " + e.getMessage());
            throw new ServiceException();
        }
        return portDTOs;
    }

    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return portDAO.getNumOfRows(query);
        } catch (DAOException e) {
            log.error("Port service getNumOfRows error " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    private static void validateSQLError(DAOException e) throws InvalidInputException {
        String message = e.getMessage();
        if (message != null && message.contains("Duplicate entry")) {
            throw new InvalidInputException("error.port.name.exist", e);
        }
    }
}

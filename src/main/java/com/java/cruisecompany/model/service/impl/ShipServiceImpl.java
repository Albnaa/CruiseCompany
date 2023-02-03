package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchShipException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.repository.ShipDAO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.MapperDTO;
import com.java.cruisecompany.model.utils.ValidationUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOToShip;
import static com.java.cruisecompany.model.utils.MapperDTO.mapShipToDTO;
import static com.java.cruisecompany.model.utils.ValidationUtil.validateOnlyLettersWithSpaces;

public class ShipServiceImpl implements ShipService {
    private final ShipDAO shipDAO;
    private final RouteService routeService;

    public ShipServiceImpl(ShipDAO shipDAO, RouteService routeService) {
        this.shipDAO = shipDAO;
        this.routeService = routeService;
    }

    @Override
    public void create(ShipDTO shipDTO) throws ServiceException {
        try {
            shipDAO.create(mapDTOToShip(shipDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(ShipDTO shipDTO) throws ServiceException {
        validateOnlyLettersWithSpaces(shipDTO.getName(), "error.ship.name");
        try {
            shipDAO.update(mapDTOToShip(shipDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            shipDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ShipDTO> findById(long id) throws ServiceException {
        ShipDTO shipDTO;
        try {
            shipDTO = mapShipToDTO(shipDAO.findById(id).orElseThrow(NoSuchShipException::new));
            if (shipDTO.getRoute() != null) {
                shipDTO.setRoute(routeService.findById(shipDTO.getRoute().getId()).get());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Optional.of(shipDTO);
    }

    @Override
    public List<ShipDTO> findAll() throws ServiceException {
        List<ShipDTO> shipDTOs;
        try {
            shipDTOs = shipDAO.findAll().stream()
                    .map(MapperDTO::mapShipToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return shipDTOs;
    }

    @Override
    public List<ShipDTO> findSortedWithRoutes(String query) throws ServiceException {
        List<ShipDTO> shipDTOs;
        try {
            shipDTOs = shipDAO.findSortedWithRoutes(query).stream()
                    .map(MapperDTO::mapShipToDTO)
                    .peek(ship -> {
                        try {
                            ship.setRoute(routeService.findById(ship.getRoute().getId()).get());
                        } catch (ServiceException e) {
                            throw new RuntimeException(e);
                        }})
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return shipDTOs;
    }

    @Override
    public List<ShipDTO> findSorted(String query) throws ServiceException {
        List<ShipDTO> shipDTOs;
        try {
            shipDTOs = shipDAO.findSorted(query).stream()
                    .map(MapperDTO::mapShipToDTO)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return shipDTOs;
    }

    @Override
    public long getNumOfRows(String query) throws ServiceException { //not sure
        try {
            return shipDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addRoute(long shipId, long routeId) throws ServiceException {
        try {
            shipDAO.addRoute(shipId, routeId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteRoute(long shipId) throws ServiceException {
        try {
            shipDAO.deleteRoute(shipId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

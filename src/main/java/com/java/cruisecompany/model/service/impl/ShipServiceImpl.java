package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchShipException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.dao.ShipDAO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.MapperDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOToShip;
import static com.java.cruisecompany.model.utils.MapperDTO.mapShipToDTO;

/**
 * This class implements the ShipService interface and provides methods for creating, updating, deleting,
 * finding and sorting ships. It also provides a method to get the number of rows in the database matching a given query.
 * <p>
 * The class uses a ShipDAO and RouteService objects to access the database.
 */
public class ShipServiceImpl implements ShipService {
    private final ShipDAO shipDAO;
    private final RouteService routeService;

    /**
     * Constructs a new ShipServiceImpl with the given ShipDAO.
     *
     * @param shipDAO      the ShipDAO to use for database access.
     * @param routeService the routeService to use for route service access.
     */
    public ShipServiceImpl(ShipDAO shipDAO, RouteService routeService) {
        this.shipDAO = shipDAO;
        this.routeService = routeService;
    }

    /**
     * Creates a new Ship in the database.
     *
     * @param shipDTO the ShipDTO object to be created
     * @throws ServiceException if an error occurs while creating the ShipDTO object in the database
     */
    @Override
    public void create(ShipDTO shipDTO) throws ServiceException {
        try {
            shipDAO.create(mapDTOToShip(shipDTO));
        } catch (DAOException e) {
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Updates an existing Ship in the database.
     *
     * @param shipDTO the ShipDTO object to be updated
     * @throws ServiceException if an error occurs while updating the ShipDTO object in the database
     */
    @Override
    public void update(ShipDTO shipDTO) throws ServiceException {
        try {
            shipDAO.update(mapDTOToShip(shipDTO));
        } catch (DAOException e) {
            validateSQLError(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Updates the image path of the ship with the specified ID.
     *
     * @param id        the ID of the ship to update
     * @param imagePath the new image path for the ship
     * @throws ServiceException if an error occurs while accessing the data store
     */
    @Override
    public void updateImage(long id, String imagePath) throws ServiceException {
        try {
            shipDAO.updateImage(id, imagePath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes an existing Ship from the database by id.
     *
     * @param id the id of the ShipDTO object to be deleted
     * @throws ServiceException if an error occurs while deleting the ShipDTO object from the database
     */
    @Override
    public void delete(long id) throws ServiceException {
        try {
            shipDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds an existing Ship in the database by id and returns it wrapped in an Optional.
     *
     * @param id the id of the ShipDTO object to be found
     * @return an Optional containing the found ShipDTO object, or an empty Optional if the ShipDTO was not found
     * @throws ServiceException if an error occurs while finding the ShipDTO object in the database
     */
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

    /**
     * Finds all Ship objects in the database and returns them as a List.
     *
     * @return a List containing all ShipDTO objects in the database
     * @throws ServiceException if an error occurs while finding the ShipDTO objects in the database
     */
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

    /**
     * Retrieves a sorted list of ships with associated routes that match the specified query.
     * The routes are fetched from the route service and set on the ships before they are returned.
     *
     * @param query the search query
     * @return the list of ships matching the query and having associated routes
     * @throws ServiceException if an error occurs while accessing the data store or fetching the routes
     */
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
                        }
                    })
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return shipDTOs;
    }

    /**
     * Retrieves a sorted list of all Ships based on the given query.
     *
     * @param query the query to sort by.
     * @return a list of ShipDTOs sorted by the given query.
     * @throws ServiceException if there is an error retrieving the sorted list from the persistence layer.
     */
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

    /**
     * Retrieves the number of rows from the {@link ShipDAO} based on the given query.
     *
     * @param query the query to count the number of rows for.
     * @return the number of rows that match the query.
     * @throws ServiceException if there is an error retrieving the number of rows from the persistence layer.
     */
    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return shipDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Retrieves the number of rows that match the specified query and have associated routes.
     *
     * @param query the search query
     * @return the number of rows matching the query and having associated routes
     * @throws ServiceException if an error occurs while accessing the data store
     */
    @Override
    public long getNumOfRowsWithRoutes(String query) throws ServiceException {
        try {
            return shipDAO.getNumOfRowsWithRoutes(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Adds a route to the ship with the specified ID.
     *
     * @param shipId  the ID of the ship to add the route to
     * @param routeId the ID of the route to add
     * @throws ServiceException if an error occurs while accessing the data store
     */
    @Override
    public void addRoute(long shipId, long routeId) throws ServiceException {
        try {
            shipDAO.addRoute(shipId, routeId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes the route from the ship with the specified ID.
     *
     * @param shipId the ID of the ship to delete the route from
     * @throws ServiceException if an error occurs while accessing the data store
     */
    @Override
    public void deleteRoute(long shipId) throws ServiceException {
        try {
            shipDAO.deleteRoute(shipId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Validates if the given DAOException is a SQL error caused by a duplicate entry and throws an InvalidInputException
     * with an error message if it is.
     *
     * @param e the DAOException to validate.
     * @throws InvalidInputException if the given DAOException is a SQL error caused by a duplicate entry.
     */
    private static void validateSQLError(DAOException e) throws InvalidInputException {
        String message = e.getMessage();
        if (message != null && message.contains("Duplicate entry")) {
            throw new InvalidInputException("error.ship.name.exist", e);
        }
    }
}

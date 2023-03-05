package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;

import java.util.List;

/**
 * The ShipService interface provides methods to manage the business logic related to ships.
 * <p>
 * It extends the Service interface and provides additional methods specific to ship entity.
 */
public interface ShipService extends Service<ShipDTO> {
    /**
     * Updates the image path of the ship with the given ID.
     *
     * @param id        the ID of the ship to update.
     * @param imagePath the new image path to set.
     * @throws ServiceException if an error occurs while updating the ship.
     */
    void updateImage(long id, String imagePath) throws ServiceException;

    /**
     * Finds all ships with their associated routes, sorted according to the given query.
     *
     * @param query the query to sort the ships with.
     * @return a list of ShipDTO objects representing the sorted ships with their associated routes.
     * @throws ServiceException if an error occurs while retrieving the ships.
     */
    List<ShipDTO> findSortedWithRoutes(String query) throws ServiceException;

    /**
     * Adds a route with the given ID to the ship with the given ID.
     *
     * @param shipId  the ID of the ship to add the route to.
     * @param routeId the ID of the route to add to the ship.
     * @throws ServiceException if an error occurs while adding the route to the ship.
     */
    void addRoute(long shipId, long routeId) throws ServiceException;

    /**
     * Deletes the route associated with the ship with the given ID.
     *
     * @param shipId the ID of the ship to delete the route from.
     * @throws ServiceException if an error occurs while deleting the route.
     */
    void deleteRoute(long shipId) throws ServiceException;

    /**
     * Returns the number of rows in the ship table, including only ships with routes.
     *
     * @param query the query to use to filter the number of rows.
     * @return the number of rows in the ship table.
     * @throws ServiceException if an error occurs while retrieving the number of rows.
     */
    long getNumOfRowsWithRoutes(String query) throws ServiceException;
}

package com.java.cruisecompany.model.dao;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ship;

import java.util.List;

/**
 * ShipDAO is an interface that extends the EntityDAO interface and defines additional methods for interacting with
 * the database for the Ship entity.
 */
public interface ShipDAO extends EntityDAO<Ship> {

    /**
     * Updates the image of the ship with the specified ID in the database.
     *
     * @param id the ID of the ship to update.
     * @param imagePath the new path to the image file.
     * @throws DAOException if an error occurs while accessing the database.
     */
    void updateImage(long id, String imagePath) throws DAOException;

    /**
     * Retrieves a list of sorted ship entities with their associated routes using the specified query.
     *
     * @param query the SQL query used to retrieve the sorted ship entities.
     * @return a List of Ship entities with their associated routes.
     * @throws DAOException if an error occurs while accessing the database.
     */
    List<Ship> findSortedWithRoutes(String query) throws DAOException;

    /**
     * Returns the number of rows in the result set for the specified query when executed with
     * associated routes.
     *
     * @param query the SQL query used to retrieve the number of rows.
     * @return the number of rows in the result set.
     * @throws DAOException if an error occurs while accessing the database.
     */
    long getNumOfRowsWithRoutes(String query) throws DAOException;

    /**
     * Adds a new route for the specified ship to the database.
     *
     * @param shipId  the ID of the ship to add the route to.
     * @param routeId the ID of the route to add.
     * @throws DAOException if an error occurs while accessing the database.
     */
    void addRoute(long shipId, long routeId) throws DAOException;

    /**
     * Deletes the route associated with the specified ship from the database.
     *
     * @param shipId the ID of the ship to delete the route from.
     * @throws DAOException if an error occurs while accessing the database.
     */
    void deleteRoute(long shipId) throws DAOException;
}

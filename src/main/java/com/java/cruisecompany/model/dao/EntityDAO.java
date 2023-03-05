package com.java.cruisecompany.model.dao;

import com.java.cruisecompany.exceptions.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * An interface for Data Access Object that handles the basic CRUD operations for a single entity in the database.
 *
 * @param <T> the type of the entity
 */
public interface EntityDAO<T> {
    /**
     * Creates a new entity in the database.
     *
     * @param entity the entity to be created
     * @throws DAOException if there's an error while accessing the database
     */
    void create(T entity) throws DAOException;

    /**
     * Updates an existing entity in the database.
     *
     * @param entity the entity to be updated
     * @throws DAOException if there's an error while accessing the database
     */
    void update(T entity) throws DAOException;

    /**
     * Deletes an existing entity from the database based on its ID.
     *
     * @param id the ID of the entity to be deleted
     * @throws DAOException if there's an error while accessing the database
     */
    void delete(long id) throws DAOException;

    /**
     * Finds an entity from the database based on its ID.
     *
     * @param id the ID of the entity to be found
     * @return an Optional containing the entity if it exists, or an empty Optional if it doesn't exist
     * @throws DAOException if there's an error while accessing the database
     */
    Optional<T> findById(long id) throws DAOException;

    /**
     * Finds all entities of this type in the database.
     *
     * @return a list of all entities
     * @throws DAOException if there's an error while accessing the database
     */
    List<T> findAll() throws DAOException;

    /**
     * Finds all entities of this type in the database and returns them sorted based on a query.
     *
     * @param query the query to sort the entities
     * @return a list of all entities sorted
     * @throws DAOException if there's an error while accessing the database
     */
    List<T> findSorted(String query) throws DAOException;

    /**
     * Returns the number of rows in the database that match the given query.
     *
     * @param query the query to match the rows
     * @return the number of rows in the database that match the given query
     * @throws DAOException if there's an error while accessing the database
     */
    long getNumOfRows(String query) throws DAOException;
}

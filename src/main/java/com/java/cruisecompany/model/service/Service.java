package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * A generic service interface for creating, updating, deleting, finding and retrieving entities.
 *
 * @param <T> the type of entity this service works with
 */
public interface Service<T> {
    /**
     * Creates a new entity.
     *
     * @param entity the entity to create
     * @throws ServiceException if an error occurs while creating the entity
     */
    void create(T entity) throws ServiceException;

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update
     * @throws ServiceException if an error occurs while updating the entity
     */
    void update(T entity) throws ServiceException;

    /**
     * Deletes an entity with the specified ID.
     *
     * @param id the ID of the entity to delete
     * @throws ServiceException if an error occurs while deleting the entity
     */
    void delete(long id) throws ServiceException;

    /**
     * Finds an entity by ID.
     *
     * @param id the ID of the entity to find
     * @return an Optional containing the entity if found, or an empty Optional if not found
     * @throws ServiceException if an error occurs while finding the entity
     */
    Optional<T> findById(long id) throws ServiceException;

    /**
     * Returns a list of all entities.
     *
     * @return a list of all entities
     * @throws ServiceException if an error occurs while retrieving the entities
     */
    List<T> findAll() throws ServiceException;

    /**
     * Returns a list of entities sorted according to the specified query.
     *
     * @param query the query used to sort the entities
     * @return a list of sorted entities
     * @throws ServiceException if an error occurs while retrieving the entities
     */
    List<T> findSorted(String query) throws ServiceException;

    /**
     * Returns the number of rows returned by the specified query.
     *
     * @param query the query to count rows for
     * @return the number of rows returned by the query
     * @throws ServiceException if an error occurs while counting the rows
     */
    long getNumOfRows(String query) throws ServiceException;
}

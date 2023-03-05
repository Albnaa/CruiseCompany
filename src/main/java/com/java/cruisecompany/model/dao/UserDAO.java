package com.java.cruisecompany.model.dao;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * UserDAO is an interface that extends the EntityDAO interface and defines additional methods for interacting with
 * the database for the User entity.
 */
public interface UserDAO extends EntityDAO<User> {
    /**
     * Adds the specified amount to the balance of the user with the specified id.
     *
     * @param userId The id of the user to add the balance to.
     * @param amount The amount to add to the user's balance.
     * @throws DAOException If an error occurs while accessing the database.
     */
    void topUpBalance(long userId, BigDecimal amount) throws DAOException;

    /**
     * Deducts the specified amount from the balance of the user with the specified id.
     *
     * @param userId The id of the user to deduct the balance from.
     * @param amount The amount to deduct from the user's balance.
     * @throws DAOException If an error occurs while accessing the database.
     */
    void deductFromBalance(long userId, BigDecimal amount) throws DAOException;

    /**
     * Retrieves the User entity with the specified login from the database.
     *
     * @param login The login of the User entity to retrieve.
     * @return An Optional containing the retrieved User entity, or an empty Optional if no entity is found.
     * @throws DAOException If an error occurs while accessing the database.
     */
    Optional<User> findByLogin(String login) throws DAOException;

    /**
     * Retrieves all User entities from the database whose first or last name starts with the specified initials.
     *
     * @param initials The initials to search for.
     * @return A List of all User entities found in the database whose first or last name starts with the specified initials.
     * @throws DAOException If an error occurs while accessing the database.
     */
    List<User> findByInitials(String initials) throws DAOException;
}

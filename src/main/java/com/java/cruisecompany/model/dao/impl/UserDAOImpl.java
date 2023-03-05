package com.java.cruisecompany.model.dao.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.dao.GenericDAO;
import com.java.cruisecompany.model.dao.UserDAO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.dao.constant.TableFields.*;

/**
 * This class is an implementation of the UserDAO interface for performing CRUD operations on Route objects in the database.
 * <p>
 * It extends the GenericDAO class and overrides its methods to perform User-specific database queries.
 */
public class UserDAOImpl extends GenericDAO<User> implements UserDAO {
    private final static String CREATE_USER = "INSERT INTO user (login, email, password, first_name," +
            " last_name, Role_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USER = "UPDATE user SET login = ?, email = ?, " +
            "first_name = ?, last_name = ?, role_id = ? WHERE id = ?";
    private final static String DEDUCT_BALANCE = "UPDATE user SET balance = balance - ? WHERE id = ?";
    private final static String TOP_UP_BALANCE = "UPDATE user SET balance = balance + ? WHERE id = ?";
    private final static String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM user";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";
    private final static String SELECT_BY_LOGIN = SELECT_ALL + " WHERE login = ?";
    private final static String SELECT_BY_INITIALS = SELECT_ALL + " WHERE first_name = ? OR last_name = ?";
    private final static String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM user";

    /**
     * Inserts a new User object into the database.
     *
     * @param user the User object to insert
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void create(User user) throws DAOException {
        executeNoReturn(CREATE_USER, user.getLogin(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                2);
    }

    /**
     * Updates a User object in the database.
     *
     * @param user the User object to update
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void update(User user) throws DAOException {
        executeNoReturn(UPDATE_USER,
                user.getLogin(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().getIndex(),
                user.getId());
    }

    /**
     * Deducts the specified amount from the balance of the User object with the given ID.
     *
     * @param userId the ID of the user whose balance should be deducted
     * @param amount the amount to deduct from the user's balance
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void deductFromBalance(long userId, BigDecimal amount) throws DAOException {
        executeNoReturn(DEDUCT_BALANCE, amount, userId);
    }

    /**
     * Increases the balance of the User object with the given ID by the specified amount.
     *
     * @param userId the ID of the user whose balance should be increased
     * @param amount the amount to add to the user's balance
     * @throws DAOException if an error occurs while accessing the database
     */
    @Override
    public void topUpBalance(long userId, BigDecimal amount) throws DAOException {
        executeNoReturn(TOP_UP_BALANCE, amount, userId);
    }

    /**
     * Deletes a User object from the database.
     *
     * @param id the id of the User object to delete
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_USER, id);
    }

    /**
     * Retrieves a User object from the database by its id.
     *
     * @param id the id of the User object to retrieve
     * @return an Optional containing the User object, or an empty Optional if it does not exist
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public Optional<User> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    /**
     * Retrieves all User objects from the database.
     *
     * @return a List of all User objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<User> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    /**
     * Finds and returns the User object with the specified login, if one exists.
     *
     * @param login the login of the user to find
     * @return an Optional containing the user with the specified login, or an empty Optional if no such user exists
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public Optional<User> findByLogin(String login) throws DAOException {
        return executeOneReturn(SELECT_BY_LOGIN, login);
    }

    /**
     * Finds and returns a list of User objects whose first or last name starts with the specified initials.
     *
     * @param initials the initials to search for
     * @return a List of users whose first or last name starts with the specified initials
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<User> findByInitials(String initials) throws DAOException {
        return executeListReturn(SELECT_BY_INITIALS, initials, initials);
    }


    /**
     * Retrieves a List of sorted User objects from the database using a query.
     *
     * @param query the SQL query to use for sorting
     * @return a List of sorted User objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<User> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }

    /**
     * Retrieves the number of rows in the User table that match a query.
     *
     * @param query the SQL query to use for counting rows
     * @return the number of rows in the User table that match the query
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    /**
     * Maps a ResultSet object to a User entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped User entity object
     * @throws SQLException if a database access error occurs
     */
    @Override
    protected User mapToEntity(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getInt(USER_ID))
                .login(rs.getString(USER_LOGIN))
                .email(rs.getString(USER_EMAIL))
                .password(rs.getString(USER_PASSWORD))
                .firstName(rs.getString(USER_FIRST_NAME))
                .lastName(rs.getString(USER_LAST_NAME))
                .balance(rs.getBigDecimal(USER_BALANCE))
                .role(Role.getRole(rs.getInt(USER_ROLE_ID)))
                .build();
    }

}

package com.java.cruisecompany.model.dao.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.dao.GenericDAO;
import com.java.cruisecompany.model.dao.PortDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.dao.constant.TableFields.*;

/**
 * This class is an implementation of the PortDAO interface for performing CRUD operations on Port objects in the database.
 * <p>
 * It extends the GenericDAO class and overrides its methods to perform Port-specific database queries.
 */
public class PortDAOImpl extends GenericDAO<Port> implements PortDAO {
    private final static String INSERT_PORT = "INSERT INTO port (name) VALUES (?)";
    private final static String UPDATE_PORT = "UPDATE port SET name = ? WHERE id = ?";
    private final static String DELETE_PORT = "DELETE FROM port WHERE id = ?";
    private final static String SELECT_BY_ID = "SELECT * FROM port WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM port";
    private final static String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM port";

    /**
     * Inserts a new Port object into the database.
     *
     * @param port the Port object to insert
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void create(Port port) throws DAOException {
        executeNoReturn(INSERT_PORT, port.getName());
    }

    /**
     * Updates a Port object in the database.
     *
     * @param port the Port object to update
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void update(Port port) throws DAOException {
        executeNoReturn(UPDATE_PORT, port.getName(), port.getId());
    }

    /**
     * Deletes a Port object from the database.
     *
     * @param id the id of the Port object to delete
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_PORT, id);
    }

    /**
     * Retrieves a Port object from the database by its id.
     *
     * @param id the id of the Port object to retrieve
     * @return an Optional containing the Port object, or an empty Optional if it does not exist
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public Optional<Port> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    /**
     * Retrieves all Port objects from the database.
     *
     * @return a List of all Port objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Port> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    /**
     * Retrieves a List of sorted Port objects from the database using a query.
     *
     * @param query the SQL query to use for sorting
     * @return a List of sorted Port objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Port> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }


    /**
     * Retrieves the number of rows in the Port table that match a query.
     *
     * @param query the SQL query to use for counting rows
     * @return the number of rows in the Port table that match the query
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    /**
     * Maps a ResultSet object to a Port entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Port entity object
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Port mapToEntity(ResultSet rs) throws SQLException {
        return Port.builder()
                .id(rs.getInt(PORT_ID))
                .name(rs.getString(PORT_NAME))
                .build();
    }
}

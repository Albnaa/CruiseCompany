package com.java.cruisecompany.model.dao;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.connectionpool.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This abstract class provides a basic implementation for a generic Data Access Object. It contains methods that execute
 * <p>
 * SQL queries and retrieve data from the database. The implementation of the mapToEntity method is left for the
 * implementing subclasses.
 *
 * @param <T> the entity type to be retrieved or persisted by the Data Access Object.
 */
public abstract class GenericDAO<T> {
    /**
     * Executes an SQL query that does not return a result set. The query is executed with the specified arguments.
     *
     * @param query the SQL query to execute.
     * @param args  the arguments for the query.
     * @throws DAOException if an error occurs while executing the query.
     */
    public static void executeNoReturn(String query, Object... args) throws DAOException {
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            setArgs(stmt, args);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Executes an SQL query that returns a single result entity. The query is executed with the specified arguments.
     *
     * @param query the SQL query to execute.
     * @param args  the arguments for the query.
     * @return an optional containing the retrieved entity, or an empty optional if the query returns no results.
     * @throws DAOException if an error occurs while executing the query.
     */
    public Optional<T> executeOneReturn(String query, Object... args) throws DAOException {
        T entity = null;
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            setArgs(stmt, args);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    entity = mapToEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(entity);
    }

    /**
     * Executes an SQL query that returns a list of result entities. The query is executed with the specified arguments.
     *
     * @param query the SQL query to execute.
     * @param args  the arguments for the query.
     * @return a list containing the retrieved entities.
     * @throws DAOException if an error occurs while executing the query.
     */
    public List<T> executeListReturn(String query, Object... args) throws DAOException {
        List<T> list = new ArrayList<>();
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            setArgs(stmt, args);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Executes an SQL query that returns the number of rows in the result set. The query is executed with no arguments.
     *
     * @param query the SQL query to execute.
     * @return the number of rows in the result set.
     * @throws DAOException if an error occurs while executing the query.
     */
    public long executeNumOfRowsReturn(String query) throws DAOException {
        long rows = 0;
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rows = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows;
    }

    /**
     * Sets the arguments for a prepared statement.
     *
     * @param stmt the prepared statement to set the arguments for.
     * @param args the arguments to set.
     * @throws SQLException if an error occurs while setting the arguments.
     */
    private static void setArgs(PreparedStatement stmt, Object... args) throws SQLException {
        int k = 0;
        for (Object arg : args) {
            stmt.setObject(++k, arg);
        }
    }

    /**
     * Maps a result set to an entity.
     *
     * @param rs the result set to map.
     * @return the mapped entity.
     * @throws SQLException if an error occurs while mapping the result set.
     */
    protected abstract T mapToEntity(ResultSet rs) throws SQLException;
}

package com.java.cruisecompany.model.dao.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.dao.GenericDAO;
import com.java.cruisecompany.model.dao.ShipDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.dao.constant.TableFields.*;

/**
 * This class is an implementation of the ShipDAO interface for performing CRUD operations on Route objects in the database.
 * <p>
 * It extends the GenericDAO class and overrides its methods to perform Ship-specific database queries.
 */
public class ShipDAOImpl extends GenericDAO<Ship> implements ShipDAO {

    private static final String INSERT_SHIP = "INSERT INTO ship (name, capacity, visited_ports, staff, image_path) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_ROUTE = "UPDATE ship SET route_id = ? WHERE id = ?";
    private static final String UPDATE_SHIP = "UPDATE ship SET name = ?, capacity = ?, visited_ports = ?, staff = ?" +
            " WHERE id = ?";
    private static final String UPDATE_SHIP_IMAGE = "UPDATE ship SET image_path = ? WHERE id = ?";
    private static final String DELETE_SHIP = "DELETE FROM ship WHERE id = ?";
    private static final String DELETE_ROUTE = "UPDATE ship SET route_id = null WHERE id = ?";
    private static final String SELECT_ALL = "SELECT ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff, " +
            "ship.image_path, route.id, route.name, route.start_of_cruise, route.end_of_cruise, route.price FROM ship " +
            "LEFT JOIN route ON ship.route_id = route.id";

    private static final String SELECT_ALL_WITH_ROUTES = "SELECT ship.id, ship.name, ship.capacity, ship.visited_ports, " +
            "ship.staff, ship.image_path, route.id, route.name, route.start_of_cruise, route.end_of_cruise, route.price " +
            "FROM ship INNER JOIN route ON ship.route_id = route.id";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE ship.id = ?";
    private static final String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM ship LEFT JOIN route ON ship.route_id = route.id";
    private static final String SELECT_COUNT_OF_ROWS_WITH_ROUTES = "SELECT COUNT(*) FROM ship INNER JOIN route ON ship.route_id = route.id";

    /**
     * Inserts a new Ship object into the database.
     *
     * @param ship the Ship object to insert
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void create(Ship ship) throws DAOException {
        executeNoReturn(INSERT_SHIP, ship.getName(),
                ship.getCapacity(),
                ship.getVisited_ports(),
                ship.getStaff(),
                ship.getImagePath());
    }

    /**
     * Updates a Ship object in the database.
     *
     * @param ship the Ship object to update
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void update(Ship ship) throws DAOException {
        executeNoReturn(UPDATE_SHIP, ship.getName(),
                ship.getCapacity(),
                ship.getVisited_ports(),
                ship.getStaff(),
                ship.getId());
    }

    /**
     * Updates the image path of the Ship object with the specified ID.
     *
     * @param id        the ID of the ship to update
     * @param imagePath the new path of the ship's image
     * @throws DAOException if there is an error updating the image path in the database
     */
    @Override
    public void updateImage(long id, String imagePath) throws DAOException {
        executeNoReturn(UPDATE_SHIP_IMAGE, imagePath, id);
    }

    /**
     * Deletes a Ship object from the database.
     *
     * @param id the id of the Ship object to delete
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_SHIP, id);
    }

    /**
     * Retrieves a Ship object from the database by its id.
     *
     * @param id the id of the Ship object to retrieve
     * @return an Optional containing the Ship object, or an empty Optional if it does not exist
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public Optional<Ship> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    /**
     * Retrieves all Ship objects from the database.
     *
     * @return a List of all Ship objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Ship> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    /**
     * Retrieves a List of sorted Ship objects from the database using a query.
     *
     * @param query the SQL query to use for sorting
     * @return a List of sorted Ship objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Ship> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }
    
    /**
     * Retrieves a List of sorted Ship objects with routes from the database using a query.
     *
     * @param query the SQL query to use for sorting
     * @return a List of sorted Ship objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Ship> findSortedWithRoutes(String query) throws DAOException {
        return executeListReturn(SELECT_ALL_WITH_ROUTES + query);
    }

    /**
     * Retrieves the number of rows in the Ship table that match a query.
     *
     * @param query the SQL query to use for counting rows
     * @return the number of rows in the Ship table that match the query
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }

    /**
     * Retrieves the number of rows in the Ship table with routes that match a query.
     *
     * @param query the SQL query to use for counting rows
     * @return the number of rows in the Ship table with routes that match the query
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public long getNumOfRowsWithRoutes(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS_WITH_ROUTES + query);
    }

    /**
     * Adds a route to the Ship object with the specified ID.
     *
     * @param shipId  the ID of the ship to add the route to
     * @param routeId the ID of the route to add to the ship
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void addRoute(long shipId, long routeId) throws DAOException {
        executeNoReturn(INSERT_ROUTE, routeId, shipId);
    }

    /**
     * Deletes the route associated with the Ship object with the specified ID.
     *
     * @param shipId the ID of the ship to delete the route from
     * @throws DAOException if there is an error deleting the route from the ship in the database
     */
    @Override
    public void deleteRoute(long shipId) throws DAOException {
        executeNoReturn(DELETE_ROUTE, shipId);
    }

    /**
     * Maps a ResultSet object to a Ship entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Ship entity object
     * @throws SQLException if a database access error occurs
     */
    @Override
    protected Ship mapToEntity(ResultSet rs) throws SQLException {
        Ship.ShipBuilder builder = Ship.builder()
                .id(rs.getInt(SHIP_ID))
                .name(rs.getString(SHIP_NAME))
                .capacity(rs.getInt(SHIP_CAPACITY))
                .visited_ports(rs.getInt(SHIP_VISITED_PORTS))
                .staff(rs.getInt(SHIP_STAFF))
                .imagePath(rs.getString(SHIP_IMAGE_PATH));

        if (rs.getString(ROUTE_NAME) != null) {
            builder.route(mapToRoute(rs));
        }

        return builder.build();
    }

    /**
     * Maps a ResultSet object to a Route entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Route entity object
     * @throws SQLException if a database access error occurs
     */
    private Route mapToRoute(ResultSet rs) throws SQLException {
        return Route.builder()
                .id(rs.getInt(ROUTE_ID))
                .name(rs.getString(ROUTE_NAME))
                .startOfCruise(rs.getDate(ROUTE_START_OF_CRUISE).toLocalDate())
                .endOfCruise(rs.getDate(ROUTE_END_OF_CRUISE).toLocalDate())
                .price(rs.getBigDecimal(ROUTE_PRICE))
                .build();
    }

}

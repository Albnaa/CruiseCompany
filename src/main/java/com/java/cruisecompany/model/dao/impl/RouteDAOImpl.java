package com.java.cruisecompany.model.dao.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.connectionpool.DBManager;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import com.java.cruisecompany.model.dao.GenericDAO;
import com.java.cruisecompany.model.dao.RouteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.java.cruisecompany.model.dao.constant.TableFields.*;

/**
 * This class is an implementation of the RouteDAO interface for performing CRUD operations on Route objects in the database.
 * <p>
 * It extends the GenericDAO class and overrides its methods to perform Route-specific database queries.
 */
public class RouteDAOImpl extends GenericDAO<Route> implements RouteDAO {
    private final static String INSERT_ROUTE = "INSERT INTO route (name, start_of_cruise, end_of_cruise, price) VALUES (?, ?, ?, ?)";
    private final static String INSERT_WAYPOINT = "INSERT INTO route_has_port (route_id, port_id, arrive_time, departure_time) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_ROUTE = "UPDATE route SET name = ?, start_of_cruise = ?, end_of_cruise = ?, price = ? WHERE id = ?";
    private final static String DELETE_ROUTE = "DELETE FROM route WHERE id = ?";
    private final static String DELETE_WAYPOINT = "DELETE FROM route_has_port WHERE route_id = ? AND port_id = ?";
    private final static String SELECT_ALL = "SELECT * FROM route";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE route.id = ?";
    private final static String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) from route";
    private final static String SELECT_ALL_ROUTE_WAYPOINTS = "SELECT port.id, port.name, route_has_port.arrive_time, " +
            "route_has_port.departure_time FROM route LEFT JOIN route_has_port ON route.id = route_has_port.route_id " +
            "INNER JOIN port ON port.id = route_has_port.port_id WHERE route.id = ?";

    /**
     * Inserts a new Route object into the database.
     *
     * @param route the Route object to insert
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void create(Route route) throws DAOException {
        executeNoReturn(INSERT_ROUTE, route.getName(),
                route.getStartOfCruise(),
                route.getEndOfCruise(),
                route.getPrice());
    }

    /**
     * Updates a Route object in the database.
     *
     * @param route the Route object to update
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void update(Route route) throws DAOException {
        executeNoReturn(UPDATE_ROUTE, route.getName(),
                route.getStartOfCruise(),
                route.getEndOfCruise(),
                route.getPrice(),
                route.getId());
    }

    /**
     * Deletes a Route object from the database.
     *
     * @param id the id of the Route object to delete
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_ROUTE, id);
    }

    /**
     * Retrieves a Route object from the database by its id.
     *
     * @param id the id of the Route object to retrieve
     * @return an Optional containing the Route object, or an empty Optional if it does not exist
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public Optional<Route> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    /**
     * Retrieves all Route objects from the database.
     *
     * @return a List of all Route objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Route> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    /**
     * Retrieves a List of sorted Route objects from the database using a query.
     *
     * @param query the SQL query to use for sorting
     * @return a List of sorted Route objects
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public List<Route> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }

    /**
     * Retrieves the number of rows in the Route table that match a query.
     *
     * @param query the SQL query to use for counting rows
     * @return the number of rows in the Route table that match the query
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
    }


    /**
     * Deletes a waypoint from a route given its routeId and portId.
     *
     * @param routeId the id of the route from which to delete the waypoint
     * @param portId  the id of the port to delete from the route
     * @throws DAOException if there is an error executing the database query
     */
    @Override
    public void deleteWaypoint(long routeId, long portId) throws DAOException {
        executeNoReturn(DELETE_WAYPOINT, routeId, portId);
    }

    /**
     * Adds a waypoint to a route given its routeId, portId, arriveTime and departureTime.
     *
     * @param routeId       the id of the route to which to add the waypoint
     * @param portId        the id of the port to add to the route
     * @param arriveTime    the date and time of arrival at the port
     * @param departureTime the date and time of departure from the port
     * @throws DAOException if there is an error with the database operation
     */
    @Override
    public void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws DAOException {
        executeNoReturn(INSERT_WAYPOINT, routeId, portId, arriveTime, departureTime);
    }

    /**
     * Retrieves a list of all waypoints of a route given its id.
     *
     * @param id the id of the route for which to retrieve the waypoints
     * @return a list of all waypoints of the route
     */
    @Override
    public List<Waypoint> getRouteWaypoints(long id) {
        List<Waypoint> list = new ArrayList<>();
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ALL_ROUTE_WAYPOINTS)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Port port = Port.builder()
                            .id(rs.getInt(PORT_ID))
                            .name(rs.getString(PORT_NAME))
                            .build();

                    list.add(Waypoint.builder()
                            .port(port)
                            .arriveTime(rs.getDate(ROUTE_HAS_PORT_ARRIVE_TIME).toLocalDate())
                            .departureTime(rs.getDate(ROUTE_HAS_PORT_DEPARTURE_TIME).toLocalDate())
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Maps a ResultSet object to a Route entity object.
     *
     * @param rs the ResultSet object containing the data to be mapped
     * @return the mapped Route entity object
     * @throws SQLException if a database access error occurs
     */
    @Override
    protected Route mapToEntity(ResultSet rs) throws SQLException {
        return Route.builder()
                .id(rs.getInt(ROUTE_ID))
                .name(rs.getString(ROUTE_NAME))
                .startOfCruise(rs.getDate(ROUTE_START_OF_CRUISE).toLocalDate())
                .endOfCruise(rs.getDate(ROUTE_END_OF_CRUISE).toLocalDate())
                .duration(rs.getInt(ROUTE_DURATION))
                .price(rs.getBigDecimal(ROUTE_PRICE))
                .build();
    }
}

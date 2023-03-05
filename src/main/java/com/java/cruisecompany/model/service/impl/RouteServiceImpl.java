package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.NoSuchRouteException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import com.java.cruisecompany.model.dao.RouteDAO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.MapperDTO;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.java.cruisecompany.model.utils.MapperDTO.mapDTOtoRoute;
import static com.java.cruisecompany.model.utils.MapperDTO.mapRouteToDTO;

/**
 * This class implements the PortService interface and provides methods for creating, updating, deleting,
 * <p>
 * finding and sorting ports. It also provides a method to get the number of rows in the database matching a given query.
 * <p>
 * The class uses a PortDAO object to access the database.
 */
public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;

    /**
     * Constructs a new RouteServiceImpl with the given RouteDAO.
     *
     * @param routeDAO the RouteDAO to use for database access.
     */
    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    /**
     * Creates a new Route in the database.
     *
     * @param routeDTO the RouteDTO object to be created
     * @throws ServiceException if an error occurs while creating the RouteDTO object in the database
     */
    @Override
    public void create(RouteDTO routeDTO) throws ServiceException {
        try {
            routeDAO.create(mapDTOtoRoute(routeDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates an existing Route in the database.
     *
     * @param routeDTO the RouteDTO object to be updated
     * @throws ServiceException if an error occurs while updating the RouteDTO object in the database
     */
    @Override
    public void update(RouteDTO routeDTO) throws ServiceException {
        try {
            routeDAO.update(mapDTOtoRoute(routeDTO));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes an existing Route from the database by id.
     *
     * @param id the id of the RouteDTO object to be deleted
     * @throws ServiceException if an error occurs while deleting the RouteDTO object from the database
     */
    @Override
    public void delete(long id) throws ServiceException {
        try {
            routeDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    /**
     * Finds an existing Route in the database by id and returns it wrapped in an Optional.
     *
     * @param id the id of the RouteDTO object to be found
     * @return an Optional containing the found RouteDTO object, or an empty Optional if the RouteDTO was not found
     * @throws ServiceException if an error occurs while finding the RouteDTO object in the database
     */
    @Override
    public Optional<RouteDTO> findById(long id) throws ServiceException {
        RouteDTO routeDTO;
        try {
            Route route = routeDAO.findById(id).orElseThrow(NoSuchRouteException::new);
            List<Waypoint> waypoints = routeDAO.getRouteWaypoints(id);
            waypoints.sort(Comparator.comparing(Waypoint::getArriveTime));
            route.setWaypoints(waypoints);

            routeDTO = mapRouteToDTO(route);
            routeDTO.setNumOfPorts(route.getWaypoints().size());
        } catch (DAOException e) {
            throw new ServiceException();
        }
        return Optional.of(routeDTO);
    }

    /**
     * Finds all Route objects in the database and returns them as a List.
     *
     * @return a List containing all RouteDTO objects in the database
     * @throws ServiceException if an error occurs while finding the RouteDTO objects in the database
     */
    @Override
    public List<RouteDTO> findAll() throws ServiceException {
        List<RouteDTO> routeDTOs;
        try {
            routeDTOs = routeDAO.findAll().stream()
                    .peek(route -> route.setWaypoints(routeDAO.getRouteWaypoints(route.getId())))
                    .map(MapperDTO::mapRouteToDTO)
                    .peek(route -> route.setNumOfPorts(route.getWaypoints().size()))
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routeDTOs;
    }

    /**
     * Retrieves a sorted list of all Routes based on the given query.
     *
     * @param query the query to sort by.
     * @return a list of RouteDTOs sorted by the given query.
     * @throws ServiceException if there is an error retrieving the sorted list from the persistence layer.
     */
    @Override
    public List<RouteDTO> findSorted(String query) throws ServiceException {
        List<RouteDTO> routeDTOs;
        try {
            routeDTOs = routeDAO.findSorted(query).stream()
                    .peek(route -> route.setWaypoints(routeDAO.getRouteWaypoints(route.getId())))
                    .map(MapperDTO::mapRouteToDTO)
                    .peek(route -> route.setNumOfPorts(route.getWaypoints().size()))
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routeDTOs;
    }

    /**
     * Retrieves the number of rows from the {@link RouteDAO} based on the given query.
     *
     * @param query the query to count the number of rows for.
     * @return the number of rows that match the query.
     * @throws ServiceException if there is an error retrieving the number of rows from the persistence layer.
     */
    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
            return routeDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Adds a waypoint to the specified route with the given arrival and departure times.
     *
     * @param routeId       the id of the route
     * @param portId        the id of the port to be added as a waypoint
     * @param arriveTime    the arrival time of the port
     * @param departureTime the departure time of the port
     * @throws ServiceException if there is an error while adding the waypoint
     */
    @Override
    public void addWaypoint(long routeId, long portId, LocalDate arriveTime, LocalDate departureTime) throws ServiceException {
        try {
            routeDAO.addWaypoint(routeId, portId, arriveTime, departureTime);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes a waypoint from the specified route.
     *
     * @param routeId the id of the route
     * @param portId  the id of the port to be removed as a waypoint
     * @throws ServiceException if there is an error while deleting the waypoint
     */
    @Override
    public void deleteWaypoint(long routeId, long portId) throws ServiceException {
        try {
            routeDAO.deleteWaypoint(routeId, portId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}

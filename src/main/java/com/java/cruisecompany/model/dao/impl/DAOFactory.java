package com.java.cruisecompany.model.dao.impl;

import com.java.cruisecompany.model.dao.*;

/**
 * The DAOFactory class is responsible for creating Data Access Objects (DAOs) for different entities in the system.
 * <p>
 * This class implements the Singleton pattern to ensure only one instance of the class is created.
 * The DAOFactory provides methods to retrieve DAOs for different entities in the system.
 * <p>
 * The DAOFactory has DAOs for the following entities: Port, Route, Ship, Ticket, User.
 */
public class DAOFactory {
    private static DAOFactory instance;
    private PortDAO portDAO;
    private RouteDAO routeDAO;
    private ShipDAO shipDAO;
    private TicketDAO ticketDAO;
    private UserDAO userDAO;

    private DAOFactory() {
    }

    /**
     * Returns the singleton instance of the DAOFactory class.
     *
     * @return The singleton instance of the DAOFactory class.
     */
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * Returns a PortDAO instance. If a PortDAO instance has not yet been created, a new one is created and returned.
     *
     * @return A PortDAO instance.
     */
    public PortDAO getPortDAO() {
        if (portDAO == null) {
            portDAO = new PortDAOImpl();
        }
        return portDAO;
    }

    /**
     * Returns a RouteDAO instance. If a RouteDAO instance has not yet been created, a new one is created and returned.
     *
     * @return A RouteDAO instance.
     */
    public RouteDAO getRouteDAO() {
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
        }
        return routeDAO;
    }

    /**
     * Returns a ShipDAO instance. If a ShipDAO instance has not yet been created, a new one is created and returned.
     *
     * @return A ShipDAO instance.
     */
    public ShipDAO getShipDAO() {
        if (shipDAO == null) {
            shipDAO = new ShipDAOImpl();
        }
        return shipDAO;
    }

    /**
     * Returns a TicketDAO instance. If a TicketDAO instance has not yet been created, a new one is created and returned.
     *
     * @return A TicketDAO instance.
     */
    public TicketDAO getTicketDAO() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAOImpl();
        }
        return ticketDAO;
    }

    /**
     * Returns a UserDAO instance. If a UserDAO instance has not yet been created, a new one is created and returned.
     *
     * @return A UserDAO instance.
     */
    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
}

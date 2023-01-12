package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.model.repository.*;
import com.java.cruisecompany.model.repository.*;
import com.java.cruisecompany.model.repository.*;

public class FactoryDAO {
    private PortDAO portDAO;
    private RouteDAO routeDAO;
    private ShipDAO shipDAO;
    private TicketDAO ticketDAO;
    private UserDAO userDAO;

    public PortDAO getPortDAO() {
        if (portDAO == null) {
            portDAO = new PortDAOImpl();
        }
        return portDAO;
    }

    public RouteDAO getRouteDAO() {
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
        }
        return routeDAO;
    }

    public ShipDAO getShipDAO() {
        if (shipDAO == null) {
            shipDAO = new ShipDAOImpl();
        }
        return shipDAO;
    }

    public TicketDAO getTicketDAO() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAOImpl();
        }
        return ticketDAO;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
}

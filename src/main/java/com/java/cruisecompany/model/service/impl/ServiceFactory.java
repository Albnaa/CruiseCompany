package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.dao.impl.DAOFactory;
import com.java.cruisecompany.model.service.*;
import lombok.Getter;

/**
 * A factory class responsible for creating and providing access to all service classes.
 * <p>
 * Uses a Singleton design pattern to ensure there is only one instance of this class.
 */
public class ServiceFactory {
    private static ServiceFactory instance;
    @Getter
    private final PortService portService;
    @Getter
    private final RouteService routeService;
    @Getter
    private final ShipService shipService;
    @Getter
    private final TicketService ticketService;
    @Getter
    private final UserService userService;

    /**
     * Constructs a new instance of the ServiceFactory, initializes all the services using the DAOFactory.
     */
    private ServiceFactory() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        portService = new PortServiceImpl(daoFactory.getPortDAO());
        routeService = new RouteServiceImpl(daoFactory.getRouteDAO());
        shipService = new ShipServiceImpl(daoFactory.getShipDAO(), routeService);
        ticketService = new TicketServiceImpl(daoFactory.getTicketDAO(), daoFactory.getUserDAO());
        userService = new UserServiceImpl(daoFactory.getUserDAO());
    }

    /**
     * Returns the singleton instance of this class, creates it if it does not exist.
     *
     * @return the singleton instance of the ServiceFactory.
     */
    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}

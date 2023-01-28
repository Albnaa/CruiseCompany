package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.repository.impl.DAOFactory;
import com.java.cruisecompany.model.service.*;
import lombok.Getter;

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

    private ServiceFactory() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        portService = new PortServiceImpl(daoFactory.getPortDAO());
        routeService = new RouteServiceImpl(daoFactory.getRouteDAO());
        shipService = new ShipServiceImpl(daoFactory.getShipDAO(), routeService);
        ticketService = new TicketServiceImpl(daoFactory.getTicketDAO());
        userService = new UserServiceImpl(daoFactory.getUserDAO());
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}

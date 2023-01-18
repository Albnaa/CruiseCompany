package com.java.cruisecompany.controller.appcontext;

import com.java.cruisecompany.model.service.*;
import com.java.cruisecompany.model.service.impl.ServiceFactory;
import lombok.Getter;

public class AppContext {
    private static AppContext instance;
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

    private AppContext() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        portService = serviceFactory.getPortService();
        routeService = serviceFactory.getRouteService();
        shipService = serviceFactory.getShipService();
        ticketService = serviceFactory.getTicketService();
        userService = serviceFactory.getUserService();
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }
}

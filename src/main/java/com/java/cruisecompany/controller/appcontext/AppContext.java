package com.java.cruisecompany.controller.appcontext;

import com.java.cruisecompany.model.service.*;
import com.java.cruisecompany.model.service.impl.ServiceFactory;
import lombok.Getter;

/**
 * AppContext represents a singleton that provides access to the application services.
 * It uses the ServiceFactory class to get the services and make them accessible from a single place.
 */
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

    /**
     * Creates a new instance of the AppContext and initializes the services
     * by getting them from the ServiceFactory.
     */
    private AppContext() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        portService = serviceFactory.getPortService();
        routeService = serviceFactory.getRouteService();
        shipService = serviceFactory.getShipService();
        ticketService = serviceFactory.getTicketService();
        userService = serviceFactory.getUserService();
    }

    /**
     * Returns the singleton instance of the AppContext, creating it if necessary.
     *
     * @return the singleton instance of the AppContext.
     */
    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }
}

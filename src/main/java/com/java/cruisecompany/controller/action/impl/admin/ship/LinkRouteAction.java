package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import static java.lang.Long.parseLong;

/**
 * Action class that handles the linking route to a ship.
 */
@Log4j2
public class LinkRouteAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the action to link a route to the ship
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the page to redirect to after the action is completed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            shipService.addRoute(parseLong(request.getParameter("id")),
                    parseLong(request.getParameter("routeId")));
        } catch (ServiceException e) {
            log.error("Error in link route action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }
}

package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * Action class that handles the unlinking route from a ship.
 */
@Log4j2
public class UnlinkRouteAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the unlink route action by deleting the link between a ship and a route.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            shipService.deleteRoute(Long.parseLong(request.getParameter("id")));
        } catch (ServiceException e) {
            log.error("Error in unlink route action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }
}

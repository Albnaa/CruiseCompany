package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * Action class that handles the deletion of a route waypoint.
 */
@Log4j2
public class DeleteWaypointAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();

    /**
     * Executes the delete waypoint action by invoking the {@link RouteService#deleteWaypoint(long, long)} method to delete
     * the specified waypoint from the route. If an exception is thrown, the error message is logged.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            routeService.deleteWaypoint(Long.parseLong(request.getParameter("id")), Long.parseLong(request.getParameter("waypointPortId")));
        } catch (Exception e) {
            log.error("Error in delete waypoint action -> " + e.getMessage());
        }

        return request.getHeader("referer");
    }
}

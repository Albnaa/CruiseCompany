package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.validation.PortValidator;
import com.java.cruisecompany.model.utils.validation.RouteValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class that handles adding a waypoint to a route.
 */
@Log4j2
public class AddWayPointAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();

    /**
     * Executes the add waypoint action by extracting request parameters and validating them. If the parameters are valid,
     * the method removes any existing errors in the session, adds a new waypoint to the specified route, and redirects the
     * user to the previous page. If any errors occur during the process, the method logs the error and returns the user to
     * the previous page with the error message.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String portId = request.getParameter("portId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        Map<String, String> errors = validateRouteParameters(portId, startDate, endDate);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        try {
            routeService.addWaypoint(Long.parseLong(id), Long.parseLong(portId), LocalDate.parse(startDate),
                    LocalDate.parse(endDate));
        } catch (Exception e) {
            log.error("Error in add waypoint action -> " + e.getMessage());
        }

        return request.getHeader("referer");
    }

    /**
     * Validates the route parameters before adding a waypoint to a route.
     *
     * @param id        the ID of the port
     * @param startDate the start date of the route
     * @param endDate   the end date of the route
     * @return a map of errors for any invalid parameters
     */
    private Map<String, String> validateRouteParameters(String id, String startDate, String endDate) {
        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortId(id, "add.waypoint", errors);
        RouteValidator.validateRouteDates(startDate, endDate, "add.waypoint", errors);
        return errors;
    }
}

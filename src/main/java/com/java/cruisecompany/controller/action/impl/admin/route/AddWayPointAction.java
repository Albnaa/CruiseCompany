package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.validation.PortValidator;
import com.java.cruisecompany.model.utils.validation.RouteValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddWayPointAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
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
            System.out.println(e.getMessage());
        }

        return request.getHeader("referer");
    }

    private Map<String, String> validateRouteParameters(String id, String startDate, String endDate) {
        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortId(id, "add.waypoint", errors);
        RouteValidator.validateRouteDates(startDate, endDate, "add.waypoint", errors);
        return errors;
    }
}

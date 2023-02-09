package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DeleteRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            routeService.delete(Long.parseLong(request.getParameter("id")));
        } catch (ServiceException e) {
            log.error("Error in delete route action -> " + e.getMessage());
        }
        return "controller?action=manage_route";
    }
}

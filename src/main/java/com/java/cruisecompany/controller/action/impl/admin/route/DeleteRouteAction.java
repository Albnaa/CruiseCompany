package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        try {
            routeService.delete(Long.parseLong(request.getParameter("id")));
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return "controller?action=manage_route";
    }
}

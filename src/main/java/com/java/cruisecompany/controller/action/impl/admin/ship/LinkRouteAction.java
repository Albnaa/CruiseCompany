package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;

import static java.lang.Long.parseLong;

public class LinkRouteAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        try {
            shipService.addRoute(parseLong(request.getParameter("id")),
                    parseLong(request.getParameter("routeId")));
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }
}

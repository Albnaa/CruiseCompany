package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

public class AddWayPointAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String id = request.getParameter("id");
        String portId = request.getParameter("portId");
        String arriveDate = request.getParameter("arriveDate");
        String departureDate = request.getParameter("departureDate");
        try {
            routeService.addWaypoint(Long.parseLong(id), Long.parseLong(portId), LocalDate.parse(arriveDate),
                    LocalDate.parse(departureDate));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return request.getHeader("referer");
    }
}

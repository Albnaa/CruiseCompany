package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DeleteWaypointAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
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

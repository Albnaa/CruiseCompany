package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.PortQueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

/**
 * An action class that handles viewing a route and its details, including its waypoints and associated ports.
 */
public class ViewRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    PortService portService = AppContext.getInstance().getPortService();

    /**
     * Executes the manage route action.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the page to redirect to after the action is completed
     */@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<RouteDTO> route = routeService.findById(id);
        route.ifPresent(routeDTO -> request.setAttribute("route", routeDTO));

        QueryBuilder queryBuilder = new PortQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("ports", portService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, portService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/route/updateRoute.jsp";
    }
}

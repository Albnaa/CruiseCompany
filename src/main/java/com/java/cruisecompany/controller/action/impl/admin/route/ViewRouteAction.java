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
 * Action class that handles viewing a route and its details, including its waypoints and associated ports.
 */
public class ViewRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    PortService portService = AppContext.getInstance().getPortService();

    /**
     * Executes the view route action by extracting the route ID from the request parameter,
     * finding the route with the corresponding ID using the routeService, and setting the found route
     * as an attribute of the request. Additionally, this method extracts query parameters from the request
     * and sets them as attributes of the request, allowing for the display of a paginated list of ports
     * that can be selected as waypoints on the updated route.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the page to redirect to after the action is completed
     */
    @Override
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

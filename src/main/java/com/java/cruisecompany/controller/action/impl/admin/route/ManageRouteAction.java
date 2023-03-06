package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.RouteQueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Action class that handles displaying and managing routes.
 */
public class ManageRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();

    /**
     * Executes the action for managing routes.
     * Retrieves query builder parameters from the request and sets them as session attributes.
     * Retrieves a sorted list of routes from the route service and sets them as a request attribute.
     * Calculates pagination and sets it as a request attribute.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page for managing routes
     * @throws ServiceException if there was an error while executing the action
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new RouteQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("routes", routeService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, routeService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/route/manageRoutes.jsp";
    }
}

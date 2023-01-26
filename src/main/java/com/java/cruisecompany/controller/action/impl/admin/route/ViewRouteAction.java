package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.PortQueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ViewRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    PortService portService = AppContext.getInstance().getPortService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        request.setAttribute("routeId", routeId);
        Optional<RouteDTO> route = routeService.findById(routeId);
        route.ifPresent(routeDTO -> request.setAttribute("route", routeDTO));

        QueryBuilder queryBuilder = new PortQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("ports", portService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, portService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "updateRoute.jsp";
    }
}

package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.RouteQueryBuilder;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ViewShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long shipId = Long.parseLong(request.getParameter("shipId"));

        try {
            Optional<ShipDTO> ship = shipService.findById(shipId);
            ship.ifPresent(s -> request.setAttribute("ship", s));

        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        QueryBuilder queryBuilder = new RouteQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("routes", routeService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, routeService.getNumOfRows(queryBuilder.buildFilterQuery()));

        return "/WEB-INF/jsp/ship/updateShip.jsp";
    }
}

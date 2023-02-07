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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

public class ViewShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long shipId = Long.parseLong(request.getParameter("id"));

        try {
            Optional<ShipDTO> ship = shipService.findById(shipId);
            ship.ifPresent(shipDTO -> request.setAttribute("ship", shipDTO));
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

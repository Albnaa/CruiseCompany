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
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

/**
 * Action class that handles the request to view a ship page.
 */
@Log4j2
public class ViewShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    RouteService routeService = AppContext.getInstance().getRouteService();

    /**
     * Executes the action to view the ship.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page to display the user's profile
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long shipId = Long.parseLong(request.getParameter("id"));

        try {
            Optional<ShipDTO> ship = shipService.findById(shipId);
            ship.ifPresent(shipDTO -> request.setAttribute("ship", shipDTO));
        } catch (ServiceException e) {
            log.error("Error in view ship action -> " + e.getMessage());
        }

        QueryBuilder queryBuilder = new RouteQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("routes", routeService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, routeService.getNumOfRows(queryBuilder.buildFilterQuery()));

        return "/WEB-INF/jsp/ship/updateShip.jsp";
    }
}

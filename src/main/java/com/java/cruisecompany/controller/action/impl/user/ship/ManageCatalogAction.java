package com.java.cruisecompany.controller.action.impl.user.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.ShipQueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * An implementation of the Action interface that handles managing the catalog of ships.
 * <p>
 * This class retrieves the list of ships with their routes from the database based on the user's query
 * parameters, sets the appropriate request attributes, and calculates the pagination information for the view.
 *
 * @author Oleh Oliinyk
 * @version 1.0
 */
@Log4j2
public class ManageCatalogAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the action to manage the catalog of ships.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page to manage the catalog of ships
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        QueryBuilder queryBuilder = new ShipQueryBuilder();
        try {
            queryBuilder.extractBuilderParameters(request);
            SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
            request.setAttribute("cruises", shipService.findSortedWithRoutes(queryBuilder.buildQuery()));
            Pagination.calculatePages(request, shipService.getNumOfRowsWithRoutes(queryBuilder.buildFilterQuery()));
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            log.error("Error in manage catalog action -> " + e.getMessage());
            request.getSession().setAttribute("error", e.getMessage());
        }
        return "/WEB-INF/jsp/ship/manageCatalog.jsp";
    }
}

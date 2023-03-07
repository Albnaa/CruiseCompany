package com.java.cruisecompany.controller.action.impl.admin.ship;

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

/**
 * Action class that handles displaying and managing ship records.
 */
public class ManageShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the action of displaying ships by building a QueryBuilder instance, extracting builder parameters from
     * the HttpServletRequest, setting session attributes from the request, retrieving a sorted list of ships from the
     * ship service based on the query built from the QueryBuilder instance, and calculating pagination for the
     * retrieved ships list.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP for managing ships
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new ShipQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("ships", shipService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, shipService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/ship/manageShips.jsp";
    }
}

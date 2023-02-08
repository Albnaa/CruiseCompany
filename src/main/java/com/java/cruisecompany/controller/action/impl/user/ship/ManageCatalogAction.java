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

public class ManageCatalogAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
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
            System.out.println(e.getMessage());
            request.getSession().setAttribute("error", e.getMessage());
        }
        return "/WEB-INF/jsp/ship/manageCatalog.jsp";
    }
}

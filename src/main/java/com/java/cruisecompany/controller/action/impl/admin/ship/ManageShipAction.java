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

public class ManageShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        QueryBuilder queryBuilder = new ShipQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("ships", shipService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, shipService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "ships.jsp";
    }
}

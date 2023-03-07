package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.PortQueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Action class that handles displaying and managing ports.
 */
public class ManagePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();

    /**
     * Executes the action of displaying ports by building a QueryBuilder instance, extracting builder parameters from
     * the HttpServletRequest, setting session attributes from the request, retrieving a sorted list of ports from the
     * port service based on the query built from the QueryBuilder instance, and calculating pagination for the
     * retrieved ports list.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return a String representing the JSP page to display
     * @throws ServiceException if an error occurs while executing the action
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new PortQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("ports", portService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, portService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/port/managePorts.jsp";
    }
}

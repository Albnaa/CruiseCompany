package com.java.cruisecompany.controller.action.impl.admin.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.TicketService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.TicketQueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Action class that handles the managing tickets.
 */
public class ManageTicketsAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();

    /**
     * Executes the action of displaying tickets by building a QueryBuilder instance, extracting builder parameters from
     * the HttpServletRequest, setting session attributes from the request, retrieving a sorted list of tickets from the
     * ticket service based on the query built from the QueryBuilder instance, and calculating pagination for the
     * retrieved tickets list.
     *
     * @param request  the HTTP servlet request.
     * @param response the HTTP servlet response.
     * @return the path to the JSP view for managing tickets.
     * @throws ServiceException if there is a problem with the service layer.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new TicketQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("tickets", ticketService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, ticketService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/ticket/manageTickets.jsp";
    }
}

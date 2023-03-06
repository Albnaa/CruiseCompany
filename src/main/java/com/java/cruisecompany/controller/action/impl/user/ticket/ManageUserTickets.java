package com.java.cruisecompany.controller.action.impl.user.ticket;

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
 * An implementation of the Action interface that handles displaying a user's tickets.
 * <p>
 * This class builds a query using the request parameters and retrieves the appropriate tickets from
 * the database. It sets the tickets as a request attribute and returns the JSP page to display them.
 *
 * @author Oleh Oliinyk
 * @version 1.0
 */
public class ManageUserTickets implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();

    /**
     * Executes the action to display a list of tickets for a user.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page to display the user's tickets
     * @throws ServiceException if there is an error while retrieving the tickets from the database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new TicketQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("tickets", ticketService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, ticketService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/ticket/manageUserTickets.jsp";
    }
}

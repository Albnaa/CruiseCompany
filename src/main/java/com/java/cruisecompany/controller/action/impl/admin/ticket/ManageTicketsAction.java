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

public class ManageTicketsAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
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

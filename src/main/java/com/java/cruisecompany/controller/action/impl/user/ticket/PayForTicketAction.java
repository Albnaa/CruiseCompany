package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Action class that handles payment for a ticket.
 */
public class PayForTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();

    /**
     * Executes the payment action by removing any previous error from session, retrieving the id of the user from session
     * and the id of the ticket to pay from request parameters, then calling the ticketService to pay for the ticket with the
     * given ids.
     * <p>
     * If a ServiceException occurs, sets the error message in session. Finally, returns the header of the previous page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("error");
        long userId = ((UserDTO) request.getSession().getAttribute("user")).getId();
        long ticketId = Long.parseLong(request.getParameter("ticketId"));
        try {
            ticketService.payForTicket(userId, ticketId);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }
}

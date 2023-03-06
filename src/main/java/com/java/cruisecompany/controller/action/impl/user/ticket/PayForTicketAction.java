package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * An implementation of the Action interface that handles payment for a ticket.
 * <p>
 * This class retrieves the user and ticket IDs from the request, calls the TicketService to process the payment,
 * and returns the user to the previous page.
 * <p>
 * If an error occurs during the payment process, an error message is stored in the session and the user is
 * redirected back to the previous page.
 *
 * @author Oleh Oliinyk
 * @version 1.0
 */
public class PayForTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();

    /**
     * Executes the action to pay for ticket.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page to display a single ticket
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

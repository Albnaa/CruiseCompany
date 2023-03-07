package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

/**
 * Action class that handles displaying a single ticket.
 */
public class ViewTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();

    /**
     * Executes the view ticket action by retrieving and setting the ticket attribute to the request.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page to display a single ticket
     * @throws ServiceException if there is an error while retrieving the ticket information from the database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            TicketDTO ticket = ticketService.findById(parseLong(request.getParameter("ticketId"))).get();
            request.setAttribute("ticket", ticket);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        return userDTO.getRole() == Role.ADMIN ? "/WEB-INF/jsp/ticket/updateTicket.jsp"
                : "/WEB-INF/jsp/ticket/viewTicket.jsp";
    }
}

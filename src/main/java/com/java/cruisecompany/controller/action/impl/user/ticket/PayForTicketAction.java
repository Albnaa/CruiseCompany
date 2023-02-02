package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;

public class PayForTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
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

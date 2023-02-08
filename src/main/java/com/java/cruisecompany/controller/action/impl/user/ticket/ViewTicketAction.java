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

public class ViewTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            TicketDTO ticket = ticketService.findById(parseLong(request.getParameter("ticketId"))).get();
            request.setAttribute("ticket", ticket);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        return userDTO.getRole() ==
                Role.ADMIN ? "/WEB-INF/jsp/ticket/updateTicket.jsp" : "/WEB-INF/jsp/ticket/viewTicket.jsp";
    }
}

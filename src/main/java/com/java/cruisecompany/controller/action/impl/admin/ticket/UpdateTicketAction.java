package com.java.cruisecompany.controller.action.impl.admin.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class UpdateTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        TicketDTO ticket = TicketDTO.builder()
                .id(parseLong(request.getParameter("ticketId")))
                .status(Status.parse(request.getParameter("ticketStatus")))
                .build();
        try {
            ticketService.update(ticket);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }
}

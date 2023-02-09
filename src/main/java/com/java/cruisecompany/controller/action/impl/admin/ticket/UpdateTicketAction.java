package com.java.cruisecompany.controller.action.impl.admin.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.entity.enums.Status;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import static java.lang.Long.parseLong;

@Log4j2
public class UpdateTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TicketDTO ticket = TicketDTO.builder()
                .id(parseLong(request.getParameter("ticketId")))
                .status(Status.parse(request.getParameter("ticketStatus")))
                .build();
        try {
            ticketService.update(ticket);
        } catch (ServiceException e) {
            log.error("Error in update ticket action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }
}

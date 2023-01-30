package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CreateTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        UserDTO user = UserDTO.builder()
                .id(((UserDTO) request.getSession().getAttribute("user")).getId())
                .build();

        ShipDTO ship = ShipDTO.builder()
                .id(parseLong(request.getParameter("shipId")))
                .build();

        TicketDTO ticket = TicketDTO.builder()
                .passengersCount(parseInt(request.getParameter("passengersCount")))
                .price(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")))
                        .multiply(BigDecimal.valueOf(parseInt(request.getParameter("passengersCount")))))
                .user(user)
                .ship(ship)
                .build();

        try {
            ticketService.create(ticket);
        } catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return "controller?action=manage_user_tickets";
    }
}

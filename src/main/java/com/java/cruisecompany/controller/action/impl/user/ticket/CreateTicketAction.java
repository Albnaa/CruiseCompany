package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.TicketService;
import com.java.cruisecompany.model.utils.FileUploaderUtil;
import com.java.cruisecompany.model.utils.validation.TicketValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CreateTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = ((UserDTO) request.getSession().getAttribute("user")).getId();
        String passengersCount = request.getParameter("passengersCount");
        String documentPath = "";

        Map<String, String> errors = validateTicketParameters(passengersCount);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        try {
            documentPath = FileUploaderUtil.addDocument(request);
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }

        UserDTO user = UserDTO.builder()
                .id(userId)
                .build();

        ShipDTO ship = ShipDTO.builder()
                .id(parseLong(request.getParameter("shipId")))
                .build();

        TicketDTO ticket = TicketDTO.builder()
                .passengersCount(parseInt(passengersCount))
                .price(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")))
                        .multiply(BigDecimal.valueOf(parseInt(passengersCount))))
                .documentPath(documentPath)
                .user(user)
                .ship(ship)
                .build();

        try {
            ticketService.create(ticket);
        } catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return "controller?action=manage_user_tickets&userF=" + userId;
    }

    private Map<String, String> validateTicketParameters(String passengersCount) {
        Map<String, String> errors = new HashMap<>();
        TicketValidation.validateTicketPassengersCount(passengersCount, "create.ticket", errors);
        return errors;
    }
}

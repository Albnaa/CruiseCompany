package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.dto.TicketDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.TicketService;
import com.java.cruisecompany.model.utils.ExceptionUtil;
import com.java.cruisecompany.model.utils.FileUploaderUtil;
import com.java.cruisecompany.model.utils.validation.TicketValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Action class that handles creating a new ticket.
 */
@Log4j2
public class CreateTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();

    /**
     * Executes the creation ticket action by getting the necessary data from the request object,
     * validates the ticket parameters, creates a new ticket object, saves the ticket object in the database,
     * and returns the appropriate view page.
     *
     *
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the action to redirect the user to after the ticket is created
     */
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
            log.error("Error in create ticket action -> " + e.getMessage());
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
        } catch (ServiceException e) {
            errors.put("error.create.ticket.passengersCount", ExceptionUtil.getRootMessage(e));
            log.error("Error in create ticket action -> " + ExceptionUtil.getRootMessage(e));
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        return "controller?action=manage_user_tickets&userF=" + userId;
    }

    /**
     * Validates the parameters for creating a new ticket.
     *
     * @param passengersCount the number of passengers for the new ticket
     * @return a Map containing any errors that were found during validation
     */
    private Map<String, String> validateTicketParameters(String passengersCount) {
        Map<String, String> errors = new HashMap<>();
        TicketValidation.validateTicketPassengersCount(passengersCount, "create.ticket", errors);
        return errors;
    }
}

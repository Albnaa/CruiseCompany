package com.java.cruisecompany.controller.action.impl.user.ticket;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;

public class PayForTicketAction implements Action {
    TicketService ticketService = AppContext.getInstance().getTicketService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}

package com.java.cruisecompany.controller.action.impl.user.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

import static java.lang.Long.parseLong;

@Log4j2
public class ViewCruiseAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Optional<ShipDTO> ship = shipService.findById(parseLong(request.getParameter("shipId")));
            ship.ifPresent(s -> request.setAttribute("ship", s));
        } catch (ServiceException e) {
            log.error("Error in view cruise action -> " + e.getMessage());
        }
        return "/WEB-INF/jsp/ship/viewCruise.jsp";
    }
}

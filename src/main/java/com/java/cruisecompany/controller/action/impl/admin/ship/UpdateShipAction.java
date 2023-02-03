package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        ShipDTO ship = ShipDTO.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .name(request.getParameter("name"))
                .capacity(Integer.parseInt(request.getParameter("capacity")))
                .visitedPorts(Integer.parseInt(request.getParameter("visitedPorts")))
                .staff(Integer.parseInt(request.getParameter("staff")))
                .build();

        try {
            shipService.update(ship);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }
}

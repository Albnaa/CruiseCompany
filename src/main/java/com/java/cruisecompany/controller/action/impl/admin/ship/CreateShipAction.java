package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;

public class CreateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        ShipDTO ship = ShipDTO.builder()
                .name(request.getParameter("shipName"))
                .capacity(Integer.parseInt(request.getParameter("shipCapacity")))
                .visitedPorts(Integer.parseInt(request.getParameter("shipVisitedPorts")))
                .staff(Integer.parseInt(request.getParameter("shipStaff")))
                .build();

        try {
            shipService.create(ship);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return "controller?action=manage_ship";
    }
}

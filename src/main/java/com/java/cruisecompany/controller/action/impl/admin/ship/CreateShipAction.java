package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.validation.RouteValidator;
import com.java.cruisecompany.model.utils.validation.ShipValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String name = request.getParameter("name");
        String capacity = request.getParameter("capacity");
        String visitedPorts = request.getParameter("visitedPorts");
        String staff = request.getParameter("staff");

        Map<String, String> errors = validateRouteParameters(name, capacity, visitedPorts, staff);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        ShipDTO ship = ShipDTO.builder()
                .name(name)
                .capacity(Integer.parseInt(capacity))
                .visitedPorts(Integer.parseInt(visitedPorts))
                .staff(Integer.parseInt(staff))
                .build();

        try {
            shipService.create(ship);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return "controller?action=manage_ship";
    }

    private Map<String, String> validateRouteParameters(String name, String capacity, String visitedPorts, String staff) {
        Map<String, String> errors = new HashMap<>();
        ShipValidator.validateShipName(name, "create.ship", errors);
        ShipValidator.validateShipCapacity(capacity, "create.ship", errors);
        ShipValidator.validateShipVisitedPorts(visitedPorts, "create.ship", errors);
        ShipValidator.validateShipStaff(staff, "create.ship", errors);
        return errors;
    }
}

package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.validation.ShipValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

import static com.java.cruisecompany.model.utils.ExceptionUtil.remapMessage;

@Log4j2
public class UpdateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String capacity = request.getParameter("capacity");
        String visitedPorts = request.getParameter("visitedPorts");
        String staff = request.getParameter("staff");

        Map<String, String> errors = validateRouteParameters(id, name, capacity, visitedPorts, staff);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        ShipDTO ship = ShipDTO.builder()
                .id(Long.parseLong(id))
                .name(name)
                .capacity(Integer.parseInt(capacity))
                .visitedPorts(Integer.parseInt(visitedPorts))
                .staff(Integer.parseInt(staff))
                .build();

        try {
            shipService.update(ship);
        } catch (ServiceException e) {
            log.error("Error in create ship action -> " + e.getMessage());

            errors.put(remapMessage(e.getMessage(), "update.ship"), e.getMessage());
            request.getSession().setAttribute("errors", errors);
        }
        return request.getHeader("referer");
    }

    private Map<String, String> validateRouteParameters(String id, String name, String capacity, String visitedPorts, String staff) {
        Map<String, String> errors = new HashMap<>();
        ShipValidator.validateShipId(id, "update.ship", errors);
        ShipValidator.validateShipName(name, "update.ship", errors);
        ShipValidator.validateShipCapacity(capacity, "update.ship", errors);
        ShipValidator.validateShipVisitedPorts(visitedPorts, "update.ship", errors);
        ShipValidator.validateShipStaff(staff, "update.ship", errors);
        return errors;
    }
}

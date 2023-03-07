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

/**
 * Action class that handles the updating of a ship.
 */
@Log4j2
public class UpdateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the update ship action by updating the properties of the ship with the given id.
     * Validates the input parameters and updates the ship using the ShipService. If there are errors in the input
     * parameters, sets the errors in the session and redirects to the previous page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
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

    /**
     * Validates the parameters needed to update a ship.
     *
     * @param id           the id of the ship to validate
     * @param name         the name of the ship to validate
     * @param capacity     the capacity of the ship to validate
     * @param visitedPorts the ports the ship has visited to validate
     * @param staff        the staff count of the ship to validate
     * @return a map of error messages for any invalid parameters
     */
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

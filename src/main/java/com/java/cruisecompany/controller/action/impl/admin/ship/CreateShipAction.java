package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.FileUploaderUtil;
import com.java.cruisecompany.model.utils.validation.ShipValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.java.cruisecompany.model.utils.ExceptionUtil.remapMessage;


@Log4j2
public class CreateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String capacity = request.getParameter("capacity");
        String visitedPorts = request.getParameter("visitedPorts");
        String staff = request.getParameter("staff");
        String imagePath = "";

        Map<String, String> errors = validateShipParameters(name, capacity, visitedPorts, staff);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        try {
            imagePath = FileUploaderUtil.addImage(request);
        } catch (IOException | ServletException e) {
            log.error("Error in create ship action -> " + e.getMessage());
        }

        ShipDTO ship = ShipDTO.builder()
                .name(name)
                .capacity(Integer.parseInt(capacity))
                .visitedPorts(Integer.parseInt(visitedPorts))
                .staff(Integer.parseInt(staff))
                .imagePath(imagePath)
                .build();

        try {
            shipService.create(ship);
        } catch (ServiceException e) {
            log.error("Error in create ship action -> " + e.getMessage());

            errors.put(remapMessage(e.getMessage(), "create.ship"), e.getMessage());
            request.getSession().setAttribute("errors", errors);
        }
        return "controller?action=manage_ship";
    }

    private Map<String, String> validateShipParameters(String name, String capacity, String visitedPorts, String staff) {
        Map<String, String> errors = new HashMap<>();
        ShipValidator.validateShipName(name, "create.ship", errors);
        ShipValidator.validateShipCapacity(capacity, "create.ship", errors);
        ShipValidator.validateShipVisitedPorts(visitedPorts, "create.ship", errors);
        ShipValidator.validateShipStaff(staff, "create.ship", errors);
        return errors;
    }
}

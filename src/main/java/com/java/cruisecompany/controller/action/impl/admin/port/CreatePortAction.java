package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.validation.PortValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.java.cruisecompany.model.utils.ExceptionUtil.remapMessage;

/**
 * Action class that handles the creation of a new port.
 */
public class CreatePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();

    /**
     * Executes the creation port action by validating the name of the port received in the request. If the name is
     * valid, it creates a new PortDTO object with the validated name and passes it to the portService to create a new
     * port.
     * <p>
     * If the name is invalid, it stores the error messages in the session and returns the referer page to display
     * the error messages.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortName(name, "create.port", errors);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }

        request.getSession().removeAttribute("errors");
        PortDTO port = PortDTO.builder()
                .name(name)
                .build();

        try {
            portService.create(port);
        } catch (ServiceException e) {
            errors.put(remapMessage(e.getMessage(), "create.port"), e.getMessage());
            request.getSession().setAttribute("errors", errors);
        }
        return request.getHeader("referer");
    }
}

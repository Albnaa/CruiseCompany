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
 * Action class that handles updating a port.
 */
public class UpdatePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();

    /**
     * Executes the UpdatePortAction by updating a port with the given id and name from the request.
     * If the request parameters fail validation, sets the errors in the session and redirects to the previous page.
     * If the update operation throws a ServiceException, sets the error in the session and redirects to the previous page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortId(id, "update.port", errors);
        PortValidator.validatePortName(name, "update.port", errors);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        PortDTO port = PortDTO.builder()
                .id(Long.parseLong(id))
                .name(name)
                .build();
        try {
            portService.update(port);
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            errors.put(remapMessage(e.getMessage(), "update.port"), e.getMessage());
            request.getSession().setAttribute("errors", errors);
        }

        return request.getHeader("referer");
    }
}

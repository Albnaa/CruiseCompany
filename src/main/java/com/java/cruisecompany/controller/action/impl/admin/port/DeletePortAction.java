package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.validation.PortValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Action class that handles deleting a Port by its ID.
 */
public class DeletePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();

    /**
     * Executes the delete port action by first validating the request parameters, then deleting the port with the
     * given ID using the {@code portService}.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortId(id, "update.port", errors);
        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        try {
            portService.delete(Long.parseLong(id));
            request.getSession().removeAttribute("error");
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }
}

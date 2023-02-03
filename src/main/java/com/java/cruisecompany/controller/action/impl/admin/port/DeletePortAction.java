package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.validation.PortValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class DeletePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String id = request.getParameter("id");

        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortId(id, errors);
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

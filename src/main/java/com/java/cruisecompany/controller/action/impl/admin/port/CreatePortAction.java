package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.validation.PortValidator;
import com.java.cruisecompany.model.utils.validation.Validator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CreatePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortName(name, errors);

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
            errors.put("error.port.name", e.getMessage());
            request.getSession().setAttribute("errors", errors);
        }
        return request.getHeader("referer");
    }
}

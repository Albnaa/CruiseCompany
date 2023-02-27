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

public class CreatePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();
        PortValidator.validatePortName(name, "create.port" , errors);

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

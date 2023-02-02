package com.java.cruisecompany.controller.action.impl.admin.port;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.service.PortService;
import jakarta.servlet.http.HttpServletRequest;

public class CreatePortAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        PortDTO port = PortDTO.builder()
                .name(request.getParameter("name"))
                .build();
        try {
            portService.create(port);
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }
}

package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.repository.impl.PortDAOImpl;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.service.impl.PortServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class AddPortAction implements Action {
    PortService portService = new PortServiceImpl(new PortDAOImpl());

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        String name = request.getParameter("name");

        Port port = new Port(1, name);

        portService.create(port);
        return null;
    }
}

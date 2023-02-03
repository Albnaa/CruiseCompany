package com.java.cruisecompany.controller.action.impl;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.PortService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

public class TestAction implements Action {
    PortService portService = AppContext.getInstance().getPortService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("error.port.name", Arrays.asList("error.port.name"));
        try {
            throw new InvalidInputException("error.port.name.fillField");
        } catch (InvalidInputException e) {
            List<String> er = errors.get("error.port.name");
            er.add(e.getMessage());
            errors.put("error.port.name", er);
        }
        request.getSession().setAttribute("errors", errors);
        return request.getHeader("referer");
    }
}

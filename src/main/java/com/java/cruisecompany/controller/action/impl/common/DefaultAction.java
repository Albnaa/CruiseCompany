package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A default implementation of the Action interface. This implementation
 * simply returns the string "login.jsp" which refers to the login page.
 */
public class DefaultAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "login.jsp";
    }
}

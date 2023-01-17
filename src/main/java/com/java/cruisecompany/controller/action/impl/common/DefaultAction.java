package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultAction implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        return "login.jsp";
    }
}

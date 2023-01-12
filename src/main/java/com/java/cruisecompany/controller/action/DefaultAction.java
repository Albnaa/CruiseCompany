package com.java.cruisecompany.controller.action;

import jakarta.servlet.http.HttpServletRequest;

public class DefaultAction implements Action{
    @Override
    public String execute(HttpServletRequest request) {
        return "login";
    }
}

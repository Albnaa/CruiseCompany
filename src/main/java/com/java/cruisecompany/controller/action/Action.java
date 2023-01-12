package com.java.cruisecompany.controller.action;

import jakarta.servlet.http.HttpServletRequest;

public interface Action {
    String execute(HttpServletRequest request);
}

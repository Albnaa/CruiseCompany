package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public class SetLocaleAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);
        String currPage = request.getHeader("referer");
        System.out.println(currPage);
        return currPage;
    }
}

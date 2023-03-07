package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Action class that handles setting the user's preferred locale.
 */
public class SetLocaleAction implements Action {
    /**
     * Executes the set locale action by setting the user's preferred locale and redirects them to the previous page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);
        return request.getHeader("referer");
    }
}

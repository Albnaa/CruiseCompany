package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The {@code SetLocaleAction} class implements the {@code Action} interface to set the locale of the application
 * based on the user's preference.
 * <p>
 * It retrieves the selected locale from the request parameters, sets it in the user session, and then redirects
 * the user to the page they were previously on.
 */
public class SetLocaleAction implements Action {
    /**
     * Sets the user's preferred locale and redirects them to the previous page.
     *
     * @param request  the {@code HttpServletRequest} object that contains the request the client has made to the server.
     * @param response the {@code HttpServletResponse} object that contains the response the servlet sends to the client.
     * @return the previous page URI as a {@code String} that the user was on before setting the locale.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);
        return request.getHeader("referer");
    }
}

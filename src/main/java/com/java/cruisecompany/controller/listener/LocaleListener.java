package com.java.cruisecompany.controller.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;

/**
 * This class listens to the servlet request event and sets the default locale for the user's session based on their
 * preferred browser language obtained from the "Accept-Language" header of the request.
 */
public class LocaleListener implements ServletRequestListener {
    /**
     * Sets the default locale for the user's session based on their preferred language obtained from the
     * "Accept-Language" header of the request. If the user's preferred language is not English or Ukrainian, the
     * default language is set to English.
     *
     * @param sre the servlet request event object
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();

        if (session.getAttribute("locale") == null) {
            String preferredLanguage = request.getHeader("Accept-Language");
            String locale;

            if (preferredLanguage != null) {
                locale = Arrays.stream(preferredLanguage.split(","))
                        .filter(lan -> lan.contains("en") || lan.contains("uk"))
                        .findFirst().orElse("en");
            } else {
                locale = "en";
            }

            session.setAttribute("locale", locale);
        }
    }
}

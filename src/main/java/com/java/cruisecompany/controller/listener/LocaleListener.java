package com.java.cruisecompany.controller.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;

public class LocaleListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("locale") == null) {
            String preferredLanguage = request.getHeader("Accept-Language");
            String locale = Arrays.stream(preferredLanguage.split(","))
                    .filter(lan -> lan.contains("en") || lan.contains("uk"))
                    .findFirst().orElse("en");
            session.setAttribute("locale", locale);
        }
    }
}

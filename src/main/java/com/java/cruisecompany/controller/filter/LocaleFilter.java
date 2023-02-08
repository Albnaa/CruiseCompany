package com.java.cruisecompany.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class LocaleFilter implements Filter {
    String defaultLocale;
    @Override
    public void init(FilterConfig filterConfig) {
        defaultLocale = filterConfig.getInitParameter("defaultLocale");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String locale = req.getParameter("locale");
        if (locale != null && !locale.isEmpty()) {
            req.getSession().setAttribute("locale", locale);
        } else {
            req.getSession().setAttribute("locale", defaultLocale);
        }
        chain.doFilter(request, response);
    }
}

package com.java.cruisecompany.controller.filter;

import com.java.cruisecompany.model.entity.enums.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<Role, List<String>> ALLOWED_ACTIONS = new HashMap<>();
    private static final List<String> COMMON_ACTIONS;

    static {
        ALLOWED_ACTIONS.put(Role.ADMIN, Arrays.asList("sign_up", "sign_out", "set_locale", "manage_users", "delete_user",
                "view_user", "update_user", "view_self_profile", "update_self_profile", "manage_ship",
                "view_ship", "create_ship", "update_ship", "delete_ship", "unlink_route", "link_route", "manage_catalog",
                "view_cruise", "manage_tickets", "manage_user_tickets", "update_ticket", "view_ticket", "manage_route",
                "view_route", "delete_waypoint", "add_waypoint", "delete_route", "update_route", "create_route",
                "add_port", "manage_port", "create_port", "delete_port", "update_port", "test_action"));

        ALLOWED_ACTIONS.put(Role.USER, Arrays.asList("sign_up", "sign_out", "set_locale", "top_up_balance",
                "view_self_profile", "update_self_profile", "manage_catalog", "view_cruise", "manage_user_tickets",
                "pay_for_ticket", "view_ticket", "create_ticket"));

        COMMON_ACTIONS = Arrays.asList("sign_in", "sign_up", "set_locale");
        
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String action = req.getParameter("action");
        System.out.println("Action = " + action);
        if (COMMON_ACTIONS.contains(action)) {
            chain.doFilter(request, response);
        } else if (session == null || session.getAttribute("user") == null) {
            System.out.println("Redirect to the login page");
            resp.sendRedirect("login.jsp");
        } else {
            Role role = (Role) session.getAttribute("role");
            if (!ALLOWED_ACTIONS.get(role).contains(action)) {
                System.out.println("Access denied");
                resp.sendRedirect("error.jsp");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

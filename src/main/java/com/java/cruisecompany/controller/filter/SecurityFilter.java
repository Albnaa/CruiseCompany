package com.java.cruisecompany.controller.filter;

import com.java.cruisecompany.model.entity.enums.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This filter is responsible for managing security-related actions and
 * redirecting unauthorized users to the login page or denying access if necessary.
 */
@Log4j2
public class SecurityFilter implements Filter {
    /**
     * A map of allowed actions for each role.
     */
    private static final Map<Role, List<String>> ALLOWED_ACTIONS = new HashMap<>();
    /**
     * A list of common actions that do not require authorization.
     */
    private static final List<String> COMMON_ACTIONS;

    static {
        ALLOWED_ACTIONS.put(Role.ADMIN, Arrays.asList("sign_up", "sign_out", "set_locale", "manage_users", "delete_user",
                "view_user", "update_user", "view_self_profile", "update_self_profile", "manage_ship",
                "view_ship", "create_ship", "update_ship", "delete_ship", "unlink_route", "link_route", "manage_catalog",
                "view_cruise", "manage_tickets", "manage_user_tickets", "update_ticket", "view_ticket", "manage_route",
                "view_route", "delete_waypoint", "add_waypoint", "delete_route", "update_route", "create_route",
                "add_port", "manage_port", "create_port", "delete_port", "update_port", "test_action", "file",
                "update_ship_image"));

        ALLOWED_ACTIONS.put(Role.USER, Arrays.asList("sign_up", "sign_out", "set_locale", "top_up_balance",
                "view_self_profile", "update_self_profile", "manage_catalog", "view_cruise", "manage_user_tickets",
                "pay_for_ticket", "view_ticket", "create_ticket", "file"));

        COMMON_ACTIONS = Arrays.asList("sign_in", "sign_up", "set_locale");

    }

    /**
     * Initializes the filter.
     *
     * @param filterConfig a FilterConfig object containing the filter's configuration
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Applies the filter to the request and response.
     *
     * @param request  a ServletRequest object representing the client's request
     * @param response a ServletResponse object representing the server's response
     * @param chain    a FilterChain object containing the chain of filters to apply
     * @throws ServletException if the filter encounters a servlet exception
     * @throws IOException      if the filter encounters an I/O exception
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String action = req.getParameter("action");
        if (COMMON_ACTIONS.contains(action)) {
            chain.doFilter(request, response);
        } else if (session == null || session.getAttribute("user") == null) {
            log.info("Requires authorization, redirects to login page");

            resp.sendRedirect("login.jsp");
        } else {
            Role role = (Role) session.getAttribute("role");
            if (!ALLOWED_ACTIONS.get(role).contains(action)) {
                log.info("Access denied");

                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

}

package com.java.cruisecompany.controller;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.action.ActionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getActionFactory().getAction(req);
            String view = action.execute(req);
            System.out.println("PathInfo = " + req.getPathInfo());
            System.out.println("ServletPath = " + req.getServletPath());
            System.out.println("View = " + view);

            if (req.getPathInfo() != null && view.equals(req.getPathInfo().substring(1))) {
                System.out.println("request dispatcher");
                req.getRequestDispatcher(view + ".jsp").forward(req, resp);
            } else {
                System.out.println("send redirect");
                resp.sendRedirect(view + ".jsp");
            }
        } catch (Exception e) {
            throw new ServletException("Action execution failed", e);
        }
    }
}

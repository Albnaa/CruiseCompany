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

            if (req.getPathInfo() != null && view.equals(req.getPathInfo().substring(1))) {
                req.getRequestDispatcher(view + ".jsp").forward(req, resp);
            } else {
                resp.sendRedirect(view + ".jsp");
            }
        } catch (Exception e) {
            throw new ServletException("Action execution failed", e);
        }
    }
}

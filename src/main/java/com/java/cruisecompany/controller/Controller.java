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
        req.getRequestDispatcher(processRequest(req)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.sendRedirect(processRequest(req));
    }

    private String processRequest(HttpServletRequest req) {
        Action action = ActionFactory.getAction(req);
        String view = "error.jsp";
        try {
            view = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}

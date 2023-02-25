package com.java.cruisecompany.controller;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.action.ActionFactory;
import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@MultipartConfig
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(processRequest(req, resp)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(processRequest(req, resp));
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp) {
        Action action = ActionFactory.getAction(req);

        String view = "accessDenied.jsp";
        try {
            view = action.execute(req, resp);
        } catch (ServiceException e) {
            log.error("Error in controller " + e.getMessage());
        }
        return view;
    }
}

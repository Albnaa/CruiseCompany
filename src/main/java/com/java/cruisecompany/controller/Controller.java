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

/**
 * The Controller class is responsible for handling incoming HTTP requests and delegating them to appropriate
 * action classes to perform business logic. It extends the HttpServlet class provided by Java Servlet API.
 * <p>
 * This class uses the @MultipartConfig annotation to indicate that it expects requests to contain multipart/form-data,
 * which is typically used for file uploads.
 */
@Log4j2
@MultipartConfig
public class Controller extends HttpServlet {
    /**
     * This method is called by the servlet container to handle incoming GET requests. It processes the request
     * by delegating it to the appropriate action class, which performs the business logic and returns a view.
     * If the view is not "doNothing", it forwards the request and response objects to the corresponding JSP page.
     *
     * @param req  the HTTP request object
     * @param resp the HTTP response object
     * @throws ServletException if an error occurs while handling the request
     * @throws IOException      if an error occurs while forwarding the request and response objects
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = processRequest(req, resp);
        if (!view.equals("doNothing")) {
            req.getRequestDispatcher(view).forward(req, resp);
        }
    }

    /**
     * This method is called by the servlet container to handle incoming POST requests. It processes the request
     * by delegating it to the appropriate action class, which performs the business logic and returns a view.
     * It then sends a redirect response to the client with the URL of the corresponding JSP page.
     *
     * @param req  the HTTP request object
     * @param resp the HTTP response object
     * @throws IOException if an error occurs while sending the redirect response
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(processRequest(req, resp));
    }

    /**
     * This method is called by doGet() and doPost() methods to process incoming HTTP requests. It obtains
     * the corresponding action object from the ActionFactory class and calls its execute() method to perform
     * the business logic. If an error occurs while executing the action, it logs the error message using log4j2
     * and sets the view to an error page.
     *
     * @param req  the HTTP request object
     * @param resp the HTTP response object
     * @return the URL of the corresponding JSP page
     */
    private String processRequest(HttpServletRequest req, HttpServletResponse resp) {
        Action action = ActionFactory.getAction(req);

        String view = "/WEB-INF/jsp/error/accessDenied.jsp";
        try {
            view = action.execute(req, resp);
        } catch (ServiceException e) {
            log.error("Error in controller " + e.getMessage());
        }
        return view;
    }
}

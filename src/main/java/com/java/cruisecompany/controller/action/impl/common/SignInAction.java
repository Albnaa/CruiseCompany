package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class SignInAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> user = userService.findByLoginAndPass(login, password);

        if (user.isPresent()) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.get().getRole());
            return "catalog";
        } else {
            request.setAttribute("error", "Wrong username/password. Please retry");
            System.out.println("attribute error was set -> " + request.getAttribute( "error"));
            return "login";
        }
    }
}

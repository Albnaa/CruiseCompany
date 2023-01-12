package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class SignInAction implements Action{
    UserService userService = new UserServiceImpl(new UserDAOImpl());
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> user = userService.findByLoginAndPass(login, password);

        if (user.isPresent()) {
            request.getSession().setAttribute("user", user);
            return "catalog";
        } else {
            request.setAttribute("error", "Wrong username/password. Please retry");
            return "login";
        }
    }
}

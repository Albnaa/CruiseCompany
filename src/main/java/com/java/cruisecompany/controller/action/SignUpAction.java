package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class SignUpAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        User user = User.builder()
                .login(login)
                .email(email)
                .password(password)
                .first_name(firstname)
                .last_name(lastname)
                .build();

        userService.create(user);
        request.getSession().setAttribute("user", user);
        return "main";
    }
}

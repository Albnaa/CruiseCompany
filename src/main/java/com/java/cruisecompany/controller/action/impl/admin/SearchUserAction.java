package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class SearchUserAction implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl(new UserDAOImpl());

        String searchQuery = request.getParameter("userName");
        List<User> users;
        if (searchQuery.isEmpty()) {
            users = userService.findAll();
            request.setAttribute("users", users);
        }
        return "users";
    }
}

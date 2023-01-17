package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteUserAction implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl(new UserDAOImpl());

        long userId = Long.parseLong(request.getParameter("userId"));
        userService.delete(userId);
        return "users";
    }
}

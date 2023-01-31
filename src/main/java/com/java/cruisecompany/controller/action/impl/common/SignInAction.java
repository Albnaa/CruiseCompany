package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;
import java.util.Optional;

public class SignInAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<UserDTO> user = userService.findByLoginAndPass(login, password);

        if (user.isPresent()) {
            request.getSession().setAttribute("user", user.get());
            request.getSession().setAttribute("role", user.get().getRole());
            request.getSession().removeAttribute("error");

            Role role = user.get().getRole();
            if (Objects.requireNonNull(role) == Role.ADMIN) {
                return "controller?action=manage_users";
            }
            return "controller?action=manage_catalog&rows=6";
        } else {
            request.getSession().setAttribute("error", "Wrong username/password. Please retry");
            System.out.println("attribute error was set -> " + request.getAttribute( "error"));
            return "login.jsp";
        }
    }
}

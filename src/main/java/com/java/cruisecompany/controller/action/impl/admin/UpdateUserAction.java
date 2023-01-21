package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateUserAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        UserService userService = new UserServiceImpl(new UserDAOImpl());

        long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Role role = Role.getRoleFromString(request.getParameter("role"));

        UserDTO user = UserDTO.builder()
                .id(id)
                .login(login)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(role)
                .build();

        userService.update(user);
        return "controller?action=view_user&userId=" + id;
    }
}

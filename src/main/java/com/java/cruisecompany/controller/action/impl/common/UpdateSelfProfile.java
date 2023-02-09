package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Log4j2
public class UpdateSelfProfile implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDTO userDTO = UserDTO.builder()
                .id(((UserDTO) request.getSession().getAttribute("user")).getId())
                .login(request.getParameter("login"))
                .email(request.getParameter("email"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .balance(BigDecimal.valueOf(Double.parseDouble(request.getParameter("balance"))))
                .role(Role.valueOf(request.getParameter("role")))
                .build();

        try {
            userService.update(userDTO);
        } catch (ServiceException e) {
            log.error("Error in update profile action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }
}

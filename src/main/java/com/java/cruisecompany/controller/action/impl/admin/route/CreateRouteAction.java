package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.validation.RouteValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String price = request.getParameter("price");

        Map<String, String> errors = validateRouteParameters(name, startDate, endDate, price);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        RouteDTO routeDTO = RouteDTO.builder()
                .name(name)
                .startOfCruise(LocalDate.parse(startDate))
                .endOfCruise(LocalDate.parse(endDate))
                .price(BigDecimal.valueOf(Double.parseDouble(price)))
                .build();

        try {
            routeService.create(routeDTO);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }

    private Map<String, String> validateRouteParameters(String name, String startDate, String endDate, String price) {
        Map<String, String> errors = new HashMap<>();
        RouteValidator.validateRouteName(name, "create.route", errors);
        RouteValidator.validateRouteDates(startDate, endDate, "create.route", errors);
        RouteValidator.validatePrice(price, "create.route", errors);
        return errors;
    }
}

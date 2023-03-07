package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.service.RouteService;
import com.java.cruisecompany.model.utils.validation.RouteValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class that handles updating a route.
 */
@Log4j2
public class UpdateRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();

    /**
     * Executes the update route action by validating and updating the route based on the provided parameters.
     * <p>
     * If the validation fails, the errors are stored in the session and the user is redirected to the referring page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String price = request.getParameter("price");

        Map<String, String> errors = validateRouteParameters(id, name, startDate, endDate, price);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        RouteDTO routeDTO = RouteDTO.builder()
                .id(Long.parseLong(id))
                .name(name)
                .startOfCruise(LocalDate.parse(startDate))
                .endOfCruise(LocalDate.parse(endDate))
                .price(BigDecimal.valueOf(Double.parseDouble(price)))
                .build();

        try {
            routeService.update(routeDTO);
        } catch (ServiceException e) {
            log.error("Error in update route action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }

    /**
     * Validates the parameters for updating a route.
     *
     * @param id        the ID of the route to be updated
     * @param name      the name of the route
     * @param startDate the start date of the route
     * @param endDate   the end date of the route
     * @param price     the price of the route
     * @return a Map containing any validation errors, where the key is the name of the parameter and the value is the error message
     */
    private Map<String, String> validateRouteParameters(String id, String name, String startDate, String endDate, String price) {
        Map<String, String> errors = new HashMap<>();
        RouteValidator.validateRouteId(id, "update.route", errors);
        RouteValidator.validateRouteName(name, "update.route", errors);
        RouteValidator.validateRouteDates(startDate, endDate, "update.route", errors);
        RouteValidator.validatePrice(price, "update.route", errors);
        return errors;
    }
}

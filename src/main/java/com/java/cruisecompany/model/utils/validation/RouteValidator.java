package com.java.cruisecompany.model.utils.validation;

import java.time.LocalDate;
import java.util.Map;

public class RouteValidator extends Validator{
    public static void validateRouteId(String id, Map<String, String> errors) {
        validateNonNegativeLong(id, "error.route.id", errors);
    }

    public static void validatePortName(String name, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error.route.name", errors);
    }

    public static void validateRouteDate(String date, Map<String, String> errors) {
        validateDate(date, "error.route.startDate", errors);
    }

    public static void validateRouteDates(String startDate, String endDate, Map<String, String> errors) {
        validateDate(startDate, "error.route.startDate", errors);
        validateDate(endDate, "error.route.endDate", errors);
        if (LocalDate.parse(endDate).isBefore(LocalDate.parse(startDate))) {
            errors.put("error.route.endDate", "error.route.endDate");
        }
    }

    public static void validatePrice(String price, Map<String, String> errors) {
        validateNonNegativeInt(price, "error.route.price", errors);
    }
}

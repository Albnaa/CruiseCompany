package com.java.cruisecompany.model.utils.validation;

import java.time.LocalDate;
import java.util.Map;

public class RouteValidator extends Validator{
    public static void validateRouteId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    public static void validateRouteName(String name, String action, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error." + action + ".name", errors);
    }

    public static void validateRouteDate(String date, String action, Map<String, String> errors) {
        validateDate(date, "error." + action + ".startDate", errors);
    }

    public static void validateRouteDates(String startDate, String endDate, String action, Map<String, String> errors) {
        boolean isFirstValid = validateDate(startDate, "error." + action + ".startDate", errors);
        boolean isSecondValid = validateDate(endDate, "error." + action + ".endDate", errors);
        if (isFirstValid && isSecondValid && LocalDate.parse(endDate).isBefore(LocalDate.parse(startDate))) {
            errors.put("error." + action + ".endDate", "error." + action + ".endDate");
        }
    }

    public static void validatePrice(String price, String action, Map<String, String> errors) {
        validateNonNegativeDouble(price, "error." + action + ".price", errors);
    }
}

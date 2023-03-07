package com.java.cruisecompany.model.utils.validation;

import java.time.LocalDate;
import java.util.Map;

/**
 * The RouteValidator class provides static methods to validate user input for route-related actions.
 * <p>
 * It extends the Validator class.
 */
public class RouteValidator extends Validator {
    /**
     * Validates the route ID to ensure that it is a non-negative long.
     *
     * @param id     the route ID to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateRouteId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    /**
     * Validates the route name to ensure that it only contains letters.
     *
     * @param name   the route name to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateRouteName(String name, String action, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error." + action + ".name", errors);
    }

    /**
     * Validates the route start date to ensure that it is a valid date in the format yyyy-MM-dd and is not in the past.
     *
     * @param date   the route start date to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateRouteDate(String date, String action, Map<String, String> errors) {
        validateDate(date, "error." + action + ".startDate", errors);
    }

    /**
     * Validates the route start and end dates to ensure that they are valid dates in the format yyyy-MM-dd and that the
     * end date is not before the start date.
     *
     * @param startDate the route start date to validate.
     * @param endDate   the route end date to validate.
     * @param action    the action being performed (e.g. create, update).
     * @param errors    a map to hold any errors encountered during validation.
     */
    public static void validateRouteDates(String startDate, String endDate, String action, Map<String, String> errors) {
        boolean isFirstValid = validateDate(startDate, "error." + action + ".startDate", errors);
        boolean isSecondValid = validateDate(endDate, "error." + action + ".endDate", errors);
        if (isFirstValid && isSecondValid && LocalDate.parse(endDate).isBefore(LocalDate.parse(startDate))) {
            errors.put("error." + action + ".endDate", "error." + action + ".endDate");
        }
    }

    /**
     * Validates the route price to ensure that it is a non-negative double.
     *
     * @param price  the route price to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validatePrice(String price, String action, Map<String, String> errors) {
        validateNonNegativeDouble(price, "error." + action + ".price", errors);
    }
}

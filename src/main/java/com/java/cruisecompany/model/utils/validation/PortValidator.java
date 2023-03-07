package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

/**
 * The PortValidator class provides static methods to validate user input for port-related actions.
 * <p>
 * It extends the Validator class.
 */
public class PortValidator extends Validator {
    /**
     * Validates the port ID to ensure that it is a non-negative long.
     *
     * @param id     the port ID to validate.
     * @param action the action being performed (e.g. create, update, delete).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validatePortId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    /**
     * Validates the port name to ensure that it only contains letters with spaces.
     *
     * @param name   the name to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validatePortName(String name, String action, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error." + action + ".name", errors);
    }
}

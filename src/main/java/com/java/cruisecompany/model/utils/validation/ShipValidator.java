package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

/**
 * The ShipValidator class provides static methods to validate user input for ship-related actions.
 * <p>
 * It extends the Validator class.
 */
public class ShipValidator extends Validator {
    /**
     * Validates the ship ID to ensure that it is a non-negative long.
     *
     * @param id     the ship ID to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateShipId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    /**
     * Validates the ship name to ensure that it only contains letters.
     *
     * @param name   the ship name to validate.
     * @param action the action being performed (e.g. register, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateShipName(String name, String action, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error." + action + ".name", errors);
    }

    /**
     * Validates the ship capacity to ensure that it is a non-negative int.
     *
     * @param capacity the ship capacity to validate.
     * @param action   the action being performed (e.g. create, update).
     * @param errors   a map to hold any errors encountered during validation.
     */
    public static void validateShipCapacity(String capacity, String action, Map<String, String> errors) {
        validateNonNegativeInt(capacity, "error." + action + ".capacity", errors);
    }

    /**
     * Validates the ship visited ports to ensure that it is a non-negative int.
     *
     * @param visitedPorts the ship visited ports to validate.
     * @param action       the action being performed (e.g. create, update).
     * @param errors       a map to hold any errors encountered during validation.
     */
    public static void validateShipVisitedPorts(String visitedPorts, String action, Map<String, String> errors) {
        validateNonNegativeInt(visitedPorts, "error." + action + ".visitedPorts", errors);
    }

    /**
     * Validates the ship staff to ensure that it is a non-negative int.
     *
     * @param staff  the ship staff to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateShipStaff(String staff, String action, Map<String, String> errors) {
        validateNonNegativeInt(staff, "error." + action + ".staff", errors);
    }
}

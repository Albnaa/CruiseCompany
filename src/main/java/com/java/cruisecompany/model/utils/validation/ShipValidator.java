package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

public class ShipValidator extends Validator {
    public static void validateShipId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    public static void validateShipName(String name, String action, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error." + action + ".name", errors);
    }

    public static void validateShipCapacity(String capacity, String action, Map<String, String> errors) {
        validateNonNegativeInt(capacity,"error." + action + ".capacity", errors);
    }

    public static void validateShipVisitedPorts(String visitedPorts, String action, Map<String, String> errors) {
        validateNonNegativeInt(visitedPorts, "error." + action + ".visitedPorts", errors);
    }

    public static void validateShipStaff(String staff, String action, Map<String, String> errors) {
        validateNonNegativeInt(staff, "error." + action + ".staff", errors);
    }
}

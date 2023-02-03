package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

public class PortValidator extends Validator {
    public static void validatePortId(String id, Map<String, String> errors) {
        validateNonNegativeLong(id, "error.port.id", errors);
    }

    public static void validatePortName(String name, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error.port.name", errors);
    }
}

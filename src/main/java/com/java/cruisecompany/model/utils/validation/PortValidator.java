package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

public class PortValidator extends Validator {
    public static void validatePortId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action  + ".id", errors);
    }

    public static void validatePortName(String name, String action, Map<String, String> errors) {
        validateOnlyLettersWithSpaces(name, "error." + action  + ".name", errors);
    }
}

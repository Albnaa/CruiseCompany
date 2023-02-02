package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

public class TicketValidation extends Validator {
    public static void validateTicketId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    public static void validateTicketPassengersCount(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".passengersCount", errors);
    }
}

package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

/**
 * The TicketValidator class provides static methods to validate user input for ticket-related actions.
 * <p>
 * It extends the Validator class.
 */
public class TicketValidation extends Validator {
    /**
     * Validates the ticket ID to ensure that it is a non-negative long.
     *
     * @param id     the ticket ID to validate.
     * @param action the action being performed (e.g. create, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateTicketId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    /**
     * Validates the ticket passengers count to ensure that it is a non-negative long.
     *
     * @param id     the ticket passengers count  to validate.
     * @param action the action being performed (e.g. create).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateTicketPassengersCount(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".passengersCount", errors);
    }
}

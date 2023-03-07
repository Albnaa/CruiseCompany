package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

/**
 * The UserValidator class provides static methods to validate user input for user-related actions.
 * <p>
 * It extends the Validator class.
 */
public class UserValidator extends Validator {
    /**
     * Validates the user ID to ensure that it is a non-negative long.
     *
     * @param id     the user ID to validate.
     * @param action the action being performed (e.g. register, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateUserId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    /**
     * Validates the user login name to ensure that it only contains letters.
     *
     * @param name   the user login name to validate.
     * @param action the action being performed (e.g. register, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateUserLogin(String name, String action, Map<String, String> errors) {
        validateOnlyLetters(name, "error." + action + ".login", errors);
    }

    /**
     * Validates the user email to ensure that it is a valid email address.
     *
     * @param email  the user email to validate.
     * @param action the action being performed (e.g. register, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateUserEmail(String email, String action, Map<String, String> errors) {
        validateEmail(email, "error." + action + ".email", errors);
    }

    /**
     * Validates the user first name to ensure that it only contains letters.
     *
     * @param name   the user first name to validate.
     * @param action the action being performed (e.g. register, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateUserFirstName(String name, String action, Map<String, String> errors) {
        validateOnlyLetters(name, "error." + action + ".firstName", errors);
    }

    /**
     * Validates the user last name to ensure that it only contains letters.
     *
     * @param name   the user last name to validate.
     * @param action the action being performed (e.g. register, update).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateUserLastName(String name, String action, Map<String, String> errors) {
        validateOnlyLetters(name, "error." + action + ".lastName", errors);
    }

    /**
     * Validates the amount to ensure that it is a non-negative double.
     *
     * @param amount the amount to validate.
     * @param action the action being performed (e.g. top up, withdraw).
     * @param errors a map to hold any errors encountered during validation.
     */
    public static void validateTopUpAmount(String amount, String action, Map<String, String> errors) {
        validateNonNegativeDouble(amount, "error." + action + ".amount", errors);
    }

    /**
     * Validates the user password to ensure that it is a valid password.
     *
     * @param password the user password to validate.
     * @param action   the action being performed (e.g. register, update).
     * @param errors   a map to hold any errors encountered during validation.
     */
    public static void validateUserPassword(String password, String action, Map<String, String> errors) {
        validatePassword(password, "error." + action + ".password", errors);
    }

    /**
     * Confirms that the user password and confirmation password match.
     *
     * @param password        the password to be validated
     * @param confirmPassword the confirmation password to be validated
     * @param action          the action being performed (e.g. register, update).
     * @param errors          a map to hold any errors encountered during validation.
     */
    public static void confirmUserPassword(String password, String confirmPassword, String action, Map<String, String> errors) {
        confirmPassword(password, confirmPassword, "error." + action + ".confirmPassword", errors);
    }
}

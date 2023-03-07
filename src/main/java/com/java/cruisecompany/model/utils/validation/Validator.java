package com.java.cruisecompany.model.utils.validation;

import java.time.LocalDate;
import java.util.Map;

/**
 * This abstract class provides methods for validating user input fields, such as password, email, and date.
 * <p>
 * It uses regular expressions to enforce various constraints on the input fields, such as the length of the password
 * and the format of the email. The class also provides methods for validating non-negative numbers of various types.
 */
public abstract class Validator {
    /**
     * Validates the given password against a regular expression pattern and adds an error message to the errors map if
     * it does not match.
     *
     * @param password the password to be validated
     * @param message  the error message to be added to the errors map if the password does not match the pattern
     * @param errors   the map of error messages
     */
    public static void validatePassword(String password, String message, Map<String, String> errors) {
        validate(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,}$", message, errors);
    }

    /**
     * Validates that the given password and confirm password match, and adds an error message to the errors map if they
     * do not match.
     *
     * @param password        the password to be validated
     * @param confirmPassword the confirmation password to be validated
     * @param message         the error message to be added to the errors map if the password and confirm password do
     *                        not match
     * @param errors          the map of error messages
     */
    public static void confirmPassword(String password, String confirmPassword, String message, Map<String, String> errors) {
        if (!password.equals(confirmPassword)) {
            errors.put(message, message);
        }
    }

    /**
     * Validates that the given value contains only letters, and adds an error message to the errors map if it contains
     * any other characters.
     *
     * @param value   the value to be validated
     * @param message the error message to be added to the errors map if the value contains any characters other than letters
     * @param errors  the map of error messages
     */
    public static void validateOnlyLetters(String value, String message, Map<String, String> errors) {
        validate(value, "^[A-Za-z]+$|^[А-ЩЬЮЯЄІЇҐа-щьюяєіїґ']+$", message, errors);
    }

    /**
     * Validates that the given value contains only letters and spaces, and adds an error message to the errors map
     * if it contains any other characters.
     *
     * @param value   the value to be validated
     * @param message the error message to be added to the errors map if the value contains any characters other than
     *                letters or spaces
     * @param errors  the map of error messages
     */
    public static void validateOnlyLettersWithSpaces(String value, String message, Map<String, String> errors) {
        validate(value, "[\\p{L}\\p{M}\\s]*", message, errors);
    }

    /**
     * Validates the given email address against a regular expression pattern and adds an error message to the errors
     * map if it does not match.
     *
     * @param email   the email address to be validated
     * @param message the error message to be added to the errors map if the email address does not match the pattern
     * @param errors  the map of error messages
     */
    public static void validateEmail(String email, String message, Map<String, String> errors) {
        validate(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message, errors);
    }

    /**
     * Validates a date string in the format of "yyyy-MM-dd" and checks if it is before the current date.
     *
     * @param date    the date string to validate
     * @param message the error message to display if validation fails
     * @param errors  a map of error messages and their associated field names
     * @return true if the date is valid and before the current date, false otherwise
     */
    public static boolean validateDate(String date, String message, Map<String, String> errors) {
        boolean isValid = validate(date, "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message, errors);
        if (isValid && LocalDate.parse(date).isBefore(LocalDate.now())) {
            errors.put(message, message);
        }
        return isValid;
    }

    /**
     * Validates a long number to ensure that it is non-negative.
     *
     * @param number  the long number to validate
     * @param message the error message to display if validation fails
     * @param errors  a map of error messages and their associated field names
     */
    protected static void validateNonNegativeLong(String number, String message, Map<String, String> errors) {
        try {
            long value = Long.parseLong(number);
            if (value < 0) {
                errors.put(message, message);
            }
        } catch (NumberFormatException e) {
            errors.put(message, message);
        }
    }

    /**
     * Validates an integer to ensure that it is non-negative.
     *
     * @param number  the integer to validate
     * @param message the error message to display if validation fails
     * @param errors  a map of error messages and their associated field names
     */
    protected static void validateNonNegativeInt(String number, String message, Map<String, String> errors) {
        try {
            int value = Integer.parseInt(number);
            if (value < 0) {
                errors.put(message, message);
            }
        } catch (NumberFormatException e) {
            errors.put(message, message);
        }
    }

    /**
     * Validates a double number to ensure that it is non-negative.
     *
     * @param number  the double number to validate
     * @param message the error message to display if validation fails
     * @param errors  a map of error messages and their associated field names
     */
    protected static void validateNonNegativeDouble(String number, String message, Map<String, String> errors) {
        try {
            double value = Double.parseDouble(number);
            if (value < 0) {
                errors.put(message, message);
            }
        } catch (NumberFormatException e) {
            errors.put(message, message);
        }
    }


    /**
     * Validates a string using a regular expression pattern.
     *
     * @param data    the string to validate
     * @param regex   the regular expression pattern to use for validation
     * @param message the error message to display if validation fails
     * @param errors  a map of error messages and their associated field names
     * @return true if the string is valid according to the regular expression pattern, false otherwise
     */
    private static boolean validate(String data, String regex, String message, Map<String, String> errors) {
        if (data == null || data.isEmpty()) {
            errors.put((message), (message + ".fillField"));
            return false;
        } else if (!data.matches(regex)) {
            errors.put(message, message);
            return false;
        }
        return true;
    }
}

package com.java.cruisecompany.model.utils.validation;

import com.java.cruisecompany.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.util.Map;

public abstract class Validator {
    public static void validatePassword(String password, String message, Map<String, String> errors)  {
        validate(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,}$", message, errors);
    }

    public static void confirmPassword(String password, String confirmPassword, String message, Map<String, String> errors) {
        if (!password.equals(confirmPassword)) {
            errors.put(message, message);
        }
    }

    //Maximum 25 characters, only english and ukrainian letters
    public static void validateOnlyLetters(String value, String message, Map<String, String> errors){
        validate(value, "[a-zA-Zа-яА-Я]+", message, errors);
    }

    public static void validateOnlyLettersWithSpaces(String value, String message, Map<String, String> errors) {
        validate(value, "[\\p{L}\\p{M}\\s]*", message, errors);
    }

    public static void validateEmail(String email, String message, Map<String, String> errors) {
        validate(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message, errors);
    }

    public static boolean validateDate(String date, String message, Map<String, String> errors) {
        boolean isValid = validate(date, "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message, errors);
        if (isValid && LocalDate.parse(date).isBefore(LocalDate.now())) {
            errors.put(message, message);
        }
        return isValid;
    }

    public static int validateDuration(String duration, String message) throws InvalidInputException {
        try {
            int value = Integer.parseInt(duration);
            if (value < 3) {
                throw new InvalidInputException(message);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(message);
        }
    }

    protected static void validateNonNegativeLong(String number, String message, Map<String, String> errors) {
        try {
            long value = Long.parseLong(number);
            if (value < 0) {
                errors.put(message, message);
            }
        } catch(NumberFormatException e) {
            errors.put(message, message);
        }
    }

    protected static void validateNonNegativeInt(String number, String message, Map<String, String> errors) {
        try {
            int value = Integer.parseInt(number);
            if (value < 0) {
                errors.put(message, message);
            }
        } catch(NumberFormatException e) {
            errors.put(message, message);
        }
    }

    protected static void validateNonNegativeDouble(String number, String message, Map<String, String> errors) {
        try {
            double value = Double.parseDouble(number);
            if (value < 0) {
                errors.put(message, message);
            }
        } catch(NumberFormatException e) {
            errors.put(message, message);
        }
    }
    private static boolean validate(String data, String regex, String message, Map<String, String> errors) {
        if (data == null || data.isEmpty()) {
            errors.put((message), (message + ".fillField"));
            return false;
        } else if (!data.matches(regex) ) {
            errors.put(message, message);
            return false;
        }
        return true;
    }
}

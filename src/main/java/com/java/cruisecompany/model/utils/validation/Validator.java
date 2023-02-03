package com.java.cruisecompany.model.utils.validation;

import com.java.cruisecompany.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.util.Map;

public abstract class Validator {
//    public static void validatePassword(String password, String message) throws InvalidInputException {
//        validate(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,}$", message);
//    }

    public static void confirmPassword(String password, String confirmPassword, String message) throws InvalidInputException {
        if (!password.equals(confirmPassword)) {
            throw new InvalidInputException(message);
        }
    }

    //Maximum 25 characters, only english and ukrainian letters
    public static void validateOnlyLetters(String value, String message, Map<String, String> errors){
        validate(value, "[a-zA-Zа-яА-Я]+", message, errors);
    }

    public static void validateOnlyLettersWithSpaces(String value, String message, Map<String, String> errors) {
        validate(value, "[\\p{L}\\p{M}\\s]*", message, errors);
    }

//    public static void validateEmail(String email, String message) throws InvalidInputException {
//        validate(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message);
//    }
//
    public static void validateDate(String date, String message, Map<String, String> errors) {
        validate(date, "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message, errors);
        if (LocalDate.parse(date).isBefore(LocalDate.now())) {
            errors.put(message, message);
        }
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
    private static void validate(String data, String regex, String message, Map<String, String> errors) {
        if (data == null || data.isEmpty()) {
            errors.put((message), (message + ".fillField"));
//            return;
        } else if (!data.matches(regex) ) {
            errors.put(message, message);
        }
    }
}

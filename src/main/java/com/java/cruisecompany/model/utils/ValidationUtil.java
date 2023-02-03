package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.exceptions.InvalidInputException;

import java.time.LocalDate;

public class ValidationUtil {
    //Minimum eight characters, at least one letter, one number,  one special character and one uppercase character
    public static void validatePassword(String password, String message) throws InvalidInputException {
        validate(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,}$", message);
    }

    public static void confirmPassword(String password, String confirmPassword, String message) throws InvalidInputException {
        if (!password.equals(confirmPassword)) {
            throw new InvalidInputException(message);
        }
    }

    //Maximum 25 characters, only english and ukrainian letters
    public static void validateOnlyLetters(String value, String message) throws InvalidInputException {
        validate(value, "[a-zA-Zа-яА-Я]+", message);
    }

    public static void validateOnlyLettersWithSpaces(String value, String message) throws InvalidInputException {
        validate(value, "[\\p{L}\\p{M}\\s]*", message);
    }

    public static void validateEmail(String email, String message) throws InvalidInputException {
        validate(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message);
    }

    public static void validateDate(String date, String message) throws InvalidInputException {
        validate(date, "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message);
        if (LocalDate.parse(date).isBefore(LocalDate.now())) {
            throw new InvalidInputException(message);
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

    private static void validate(String data, String regex, String message) throws InvalidInputException {
        if (data == null || data.isEmpty()) {
            throw new InvalidInputException(message + ".fillField");
        } else if (!data.matches(regex) ) {
            throw new InvalidInputException(message);
        }
    }




}

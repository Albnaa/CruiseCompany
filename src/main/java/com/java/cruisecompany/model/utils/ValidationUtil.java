package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.InvalidInputException;

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
        validate(value, "^[a-zA-Zа-яА-Я]{1,25}$", message);
    }

    public static void validateEmail(String email, String message) throws InvalidInputException {
        validate(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message);
    }

    private static void validate(String data, String regex, String message) throws InvalidInputException {
        if (data == null || data.isEmpty()) {
            throw new InvalidInputException("Please fill this field");
        } else if (!data.matches(regex) ) {
            throw new InvalidInputException(message);
        }
    }




}

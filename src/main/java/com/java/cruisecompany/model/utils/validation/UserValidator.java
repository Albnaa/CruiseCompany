package com.java.cruisecompany.model.utils.validation;

import java.util.Map;

public class UserValidator extends Validator{
    public static void validateUserId(String id, String action, Map<String, String> errors) {
        validateNonNegativeLong(id, "error." + action + ".id", errors);
    }

    public static void validateUserLogin(String name, String action, Map<String, String> errors) {
        validateOnlyLetters(name, "error." + action + ".login", errors);
    }

    public static void validateUserEmail(String email, String action, Map<String, String> errors) {
        validateEmail(email, "error." + action + ".email", errors);
    }

    public static void validateUserFirstName(String name, String action, Map<String, String> errors) {
        validateOnlyLetters(name, "error." + action + ".firstName", errors);
    }

    public static void validateUserLastName(String name, String action, Map<String, String> errors) {
        validateOnlyLetters(name, "error." + action + ".lastName", errors);
    }
}

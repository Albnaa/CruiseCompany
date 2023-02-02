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

    public static void validateTopUpAmount(String amount, String action, Map<String, String> errors) {
        validateNonNegativeDouble(amount, "error." + action + ".amount", errors);
    }

    public static void validateUserPassword(String password, String action, Map<String, String> errors) {
        validatePassword(password, "error." + action + ".password", errors);
    }

    public static void confirmUserPassword(String password, String confirmPassword, String action, Map<String, String> errors) {
        confirmPassword(password, confirmPassword, "error." + action + ".confirmPassword", errors);
    }

}

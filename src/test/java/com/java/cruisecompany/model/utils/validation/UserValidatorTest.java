package com.java.cruisecompany.model.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserValidatorTest {

    Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        errors.clear();
    }
    static Stream<Arguments> validateUserIdProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("123", true),
                Arguments.of("-1", false),
                Arguments.of("", false),
                Arguments.of("abc", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateUserIdProvider")
    void validateUserId(String id, boolean expectedValid) {
        UserValidator.validateUserId(id, "validateUserId", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateUserLoginProvider() {
        return Stream.of(
                Arguments.of("Login", true),
                Arguments.of("admin", true),
                Arguments.of("Bob", true),
                Arguments.of("12331", false),
                Arguments.of("", false),
                Arguments.of("User23", false)
        );
    }


    @ParameterizedTest
    @MethodSource("validateUserLoginProvider")
    void validateUserLogin(String login, boolean expectedValid) {
        UserValidator.validateUserLogin(login, "validateUserLogin", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateUserEmailProvider() {
        return Stream.of(
                Arguments.of("test@example.com", true),
                Arguments.of("test123@example.com", true),
                Arguments.of("test@example.co.uk", true),
                Arguments.of("test@example.com.br", true),
                Arguments.of("", false),
                Arguments.of("test@", false),
                Arguments.of("test@example", false),
                Arguments.of("test@example.", false),
                Arguments.of("test@example..com", false),
                Arguments.of("test@.example.com", false),
                Arguments.of("test @example.com", false),
                Arguments.of("test@example. com", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateUserEmailProvider")
    void validateUserEmail(String email, boolean expectedValid) {
        UserValidator.validateUserEmail(email, "validateUserEmail", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateUserFirstNameProvider() {
        return Stream.of(
                Arguments.of("John", true),
                Arguments.of("Mary", true),
                Arguments.of("", false),
                Arguments.of("123", false),
                Arguments.of("John123", false),
                Arguments.of("John Smith", false),
                Arguments.of("John@Smith", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateUserFirstNameProvider")
    void validateUserFirstName(String firstName, boolean expectedValid) {
        UserValidator.validateUserFirstName(firstName, "validateUserFirstName", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateUserLastNameProvider() {
        return Stream.of(
                Arguments.of("Doe", true),
                Arguments.of("Johnson", true),
//                Arguments.of("van der Meer", true),
//                Arguments.of("O'Connell", true),
                Arguments.of("", false),
                Arguments.of("123", false),
                Arguments.of("Doe123", false),
                Arguments.of("John Doe", false),
                Arguments.of("John@Doe", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateUserLastNameProvider")
    void validateUserLastName(String lastName, boolean expectedValid) {
        UserValidator.validateUserLastName(lastName, "validateUserLastName", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateTopUpAmountProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1.5", true),
                Arguments.of("10", true),
                Arguments.of("-1", false),
                Arguments.of("", false),
                Arguments.of("abc", false),
                Arguments.of("1.234", true)
        );
    }

    @ParameterizedTest
    @MethodSource("validateTopUpAmountProvider")
    void validateTopUpAmount(String amount, boolean expectedValid) {
        UserValidator.validateTopUpAmount(amount, "validateTopUpAmount", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateUserPasswordProvider() {
        return Stream.of(
                Arguments.of("Abc123$%", true),
                Arguments.of("P@ssword!2", true),
                Arguments.of("qwertyUI1@", true),
                Arguments.of("4B#l0Ck*", true),
                Arguments.of("F1$hyS0da", true),
                Arguments.of("L0ngP@$$word123#", true),
                Arguments.of("weakPassword", false),
                Arguments.of("password", false),
                Arguments.of("123Pass", false),
                Arguments.of("@@Pass1", false),
                Arguments.of("123123412321@3e", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateUserPasswordProvider")
    void validateUserPassword(String password, boolean expectedValid) {
        UserValidator.validateUserPassword(password, "validateUserPassword", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> confirmUserPasswordProvider() {
        return Stream.of(
                Arguments.of("P@ssword123", "P@ssword123", true),
                Arguments.of("password1", "password2", false),
                Arguments.of("P@ssword123", "", false),
                Arguments.of("", "P@ssword123", false)
        );
    }

    @ParameterizedTest
    @MethodSource("confirmUserPasswordProvider")
    void confirmUserPassword(String password, String confirmPassword, boolean expectedValid) {
        UserValidator.confirmUserPassword(password, confirmPassword, "confirmUserPassword", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }
}
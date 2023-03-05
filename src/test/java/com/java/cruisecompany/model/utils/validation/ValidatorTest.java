package com.java.cruisecompany.model.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        errors.clear();
    }

    static Stream<Arguments> passwordsProvider() {
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
    @MethodSource("passwordsProvider")
    void validatePasswordSuccess(String password, boolean expectValid) {
        Validator.validatePassword(password, "password", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> confirmPasswordProvider() {
        return Stream.of(
                Arguments.of("Abc123$%", "Abc123$%", true),
                Arguments.of("P@ssword!2", "P@ssword!2", true),
                Arguments.of("qwertyUI1@", "qwertyUI1@", true),
                Arguments.of("password", "password", true),
                Arguments.of("password", "PASSWORD", false),
                Arguments.of("password", "password1", false),
                Arguments.of("password", "pass word", false),
                Arguments.of("password", "", false)
        );
    }

    @ParameterizedTest
    @MethodSource("confirmPasswordProvider")
    void confirmPassword(String password, String confirmPassword, boolean expectValid) {
        Validator.confirmPassword(password, confirmPassword, "confirmPassword", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateOnlyLettersProvider() {
        return Stream.of(
                Arguments.of("Bob", true),
                Arguments.of("UPPERCASE", true),
                Arguments.of("camelCase", true),
                Arguments.of("привіт", true),
                Arguments.of("світ", true),
                Arguments.of("солов'їний", true),
                Arguments.of("123", false),
                Arguments.of("abcd123", false),
                Arguments.of("ab-cd", false),
                Arguments.of("два слова", false),
                Arguments.of("underscore_case", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateOnlyLettersProvider")
    void validateOnlyLetters(String value, boolean expectValid) {
        Validator.validateOnlyLetters(value, "validateOnlyLetters", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateOnlyLettersWithSpacesProvider() {
        return Stream.of(
                Arguments.of("abcd", true),
                Arguments.of("ABCD", true),
                Arguments.of("abCd", true),
                Arguments.of("hello world", true),
                Arguments.of("sentence with spaces", true),
                Arguments.of("привіт світ", true),
                Arguments.of(" ", true),
                Arguments.of("123", false),
                Arguments.of("abcd123", false),
                Arguments.of("ab-cd", false),
                Arguments.of("hello12 3", false),
                Arguments.of("користувач $$", false),
                Arguments.of("№ 132", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateOnlyLettersWithSpacesProvider")
    void validateOnlyLettersWithSpaces(String value, boolean expectValid) {
        Validator.validateOnlyLettersWithSpaces(value, "validateOnlyLettersWithSpaces", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateEmailProvider() {
        return Stream.of(
                Arguments.of("example@example.com", true),
                Arguments.of("example@example.co.uk", true),
                Arguments.of("example123@example.com", true),
                Arguments.of("example@example", false),
                Arguments.of("example@.com", false),
                Arguments.of("example@example..com", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateEmailProvider")
    void validateEmail(String email, boolean expectValid) {
        Validator.validateEmail(email, "validateEmail", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateDateProvider() {
        return Stream.of(
                Arguments.of("2022-02-28", false),
                Arguments.of("2022-02-24", false),
                Arguments.of("2023-02-19", false),
                Arguments.of("2023-03-20", true),
                Arguments.of("2023-12-31", true),
                Arguments.of("2024-01-01", true),
                Arguments.of("", false),
                Arguments.of("2023-13-01", false),
                Arguments.of("2023-12-32", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateDateProvider")
    void validateDate(String date, boolean expectedValid) {
        Validator.validateDate(date, "validateDate", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateNonNegativeLongProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("-1", false),
                Arguments.of("2147483647", true),
                Arguments.of("-2147483648", false),
                Arguments.of("9223372036854775807", true),
                Arguments.of("-9223372036854775808", false),
                Arguments.of("", false),
                Arguments.of("abc", false),
                Arguments.of("1.23", false),
                Arguments.of("1,23", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateNonNegativeLongProvider")
    void validateNonNegativeLong(String number, boolean expectValid) {
        Validator.validateNonNegativeLong(number, "validateNonNegativeLong", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateNonNegativeIntProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("-1", false),
                Arguments.of("2147483647", true),
                Arguments.of("-2147483648", false),
                Arguments.of("", false),
                Arguments.of("abc", false),
                Arguments.of("1.23", false),
                Arguments.of("1,23", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateNonNegativeIntProvider")
    void validateNonNegativeInt(String number, boolean expectValid) {
        Validator.validateNonNegativeInt(number, "validateNonNegativeInt", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateNonNegativeDoubleProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("-1", false),
                Arguments.of("1.23", true),
                Arguments.of("-1.23", false),
                Arguments.of("1,23", false),
                Arguments.of("", false),
                Arguments.of("abc", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateNonNegativeDoubleProvider")
    void validateNonNegativeDouble(String number, boolean expectValid) {
        Validator.validateNonNegativeDouble(number, "validateNonNegativeDouble", errors);
        assertEquals(expectValid, errors.isEmpty());
    }
}


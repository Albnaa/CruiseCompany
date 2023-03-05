package com.java.cruisecompany.model.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteValidatorTest {
    Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        errors.clear();
    }

    @ParameterizedTest
    @MethodSource("validateRouteIdProvider")
    void validateRouteId(String id, String action, boolean expectValid) {
        RouteValidator.validateRouteId(id, action, errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateRouteIdProvider() {
        return Stream.of(
                Arguments.of("123", "create", true),
                Arguments.of("0", "update", true),
                Arguments.of("-1", "create", false),
                Arguments.of("abc", "update", false),
                Arguments.of("", "create", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateRouteNameProvider")
    void validateRouteName(String name, String action, boolean expectValid) {
        RouteValidator.validateRouteName(name, action, errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateRouteNameProvider() {
        return Stream.of(
                Arguments.of("Asia Cruise", "create", true),
                Arguments.of("Arctic cruise", "update", true),
                Arguments.of("River Cruise", "create", true),
                Arguments.of("Asia 123 Cruise", "update", false),
                Arguments.of("123", "create", false),
                Arguments.of("", "update", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateRouteDateProvider")
    void validateRouteDate(String date, String action, boolean expectValid) {
        RouteValidator.validateRouteDate(date, action, errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateRouteDateProvider() {
        return Stream.of(
                Arguments.of("2023-02-28", "create", false),
                Arguments.of("2023-12-31", "update", true),
                Arguments.of("2023-01-28", "create", false),
                Arguments.of("2022-02-28", "update", false),
                Arguments.of("2023-14-28", "create", false),
                Arguments.of("2022-12-32", "update", false),
                Arguments.of("", "create", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateRouteDatesProvider")
    void validateRouteDates(String startDate, String endDate, String action, boolean expectValid) {
        RouteValidator.validateRouteDates(startDate, endDate, action, errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    private static Stream<Arguments> validateRouteDatesProvider() {
        return Stream.of(
                Arguments.of("2022-01-01", "2022-01-02", "create", false),
                Arguments.of("2022-01-02", "2022-01-01", "create", false),
                Arguments.of("2023-05-05", "2023-05-07", "create", true),
                Arguments.of("2022-02-26", "2022-01-01", "create", false),
                Arguments.of("invalid_date", "2022-01-01", "create", false),
                Arguments.of("2022-01-01", "invalid_date", "create", false),
                Arguments.of("2023-05-01", "2023-05-02", "update", true),
                Arguments.of("2022-01-02", "2022-01-01", "update", false),
                Arguments.of("2024-01-01", "2024-01-01", "update", true),
                Arguments.of("2022-02-22", "2022-01-01", "update", false),
                Arguments.of("invalid_date", "2022-01-01", "update", false),
                Arguments.of("2022-01-01", "invalid_date", "update", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validatePriceProvider")
    void validatePrice(String price, String action, boolean expectValid) {
        RouteValidator.validatePrice(price, action, errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    private static Stream<Arguments> validatePriceProvider() {
        return Stream.of(
                Arguments.of("10.50", "create", true),
                Arguments.of("0", "create", true),
                Arguments.of("-10", "create", false),
                Arguments.of("invalid_price", "create", false),
                Arguments.of("10.50", "update", true),
                Arguments.of("0", "update", true),
                Arguments.of("-10", "update", false),
                Arguments.of("invalid_price", "update", false)
        );
    }
}
package com.java.cruisecompany.model.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketValidationTest {
    Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        errors.clear();
    }

    static Stream<Arguments> validateTicketIdProvider() {
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
    @MethodSource("validateTicketIdProvider")
    void validateTicketId(String id, boolean expectValid) {
        TicketValidation.validateTicketId(id, "validateTicketId", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validateTicketPassengersCountProvider() {
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
    @MethodSource("validateTicketPassengersCountProvider")
    void validateTicketPassengersCount(String passengersCount, boolean expectValid) {
        TicketValidation.validateTicketPassengersCount(passengersCount, "validateTicketPassengersCount", errors);
        assertEquals(expectValid, errors.isEmpty());
    }
}
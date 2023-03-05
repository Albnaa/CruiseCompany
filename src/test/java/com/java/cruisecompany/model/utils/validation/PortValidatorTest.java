package com.java.cruisecompany.model.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortValidatorTest {
    private final Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        errors.clear();
    }

    static Stream<Arguments> validatePortIdProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("-1", false),
                Arguments.of("abc", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validatePortIdProvider")
    void validatePortId(String id, boolean expectValid) {
        PortValidator.validatePortId(id, "validatePortId", errors);
        assertEquals(expectValid, errors.isEmpty());
    }

    static Stream<Arguments> validatePortNameProvider() {
        return Stream.of(
                Arguments.of("Port 1", false),
                Arguments.of("Port-1", false),
                Arguments.of("Port A", true),
                Arguments.of("New York", true),
                Arguments.of("Boston", true),
                Arguments.of("Port_1", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validatePortNameProvider")
    void validatePortName(String name, boolean expectValid) {
        PortValidator.validatePortName(name, "validatePortName", errors);
        assertEquals(expectValid, errors.isEmpty());
    }
}
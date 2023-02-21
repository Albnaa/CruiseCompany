package com.java.cruisecompany.model.utils.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipValidatorTest {

    Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        errors.clear();
    }

    static Stream<Arguments> validateShipIdProvider() {
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
    @MethodSource("validateShipIdProvider")
    void validateShipId(String id, boolean expectedValid) {
        ShipValidator.validateShipId(id, "validateShipId", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateShipNameProvider() {
        return Stream.of(
                Arguments.of("Aurora", true),
                Arguments.of("Phoenix", true),
                Arguments.of("Carnival Breeze", true),
                Arguments.of("Blue 12", false),
                Arguments.of("", false),
                Arguments.of("Ship #2", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateShipNameProvider")
    void validateShipName(String name, boolean expectedValid) {
        ShipValidator.validateShipName(name, "validateShipName", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateShipCapacityProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1000", true),
                Arguments.of("1230", true),
                Arguments.of("-1", false),
                Arguments.of("", false),
                Arguments.of("abc", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateShipCapacityProvider")
    void validateShipCapacity(String capacity, boolean expectedValid) {
        ShipValidator.validateShipCapacity(capacity, "validateShipCapacity", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateShipVisitedPortsProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("50", true),
                Arguments.of("123", true),
                Arguments.of("-1", false),
                Arguments.of("", false),
                Arguments.of("abc", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateShipVisitedPortsProvider")
    void validateShipVisitedPorts(String visitedPorts, boolean expectedValid) {
        ShipValidator.validateShipVisitedPorts(visitedPorts, "validateShipVisitedPorts", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }

    static Stream<Arguments> validateShipStaffProvider() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("50", true),
                Arguments.of("123", true),
                Arguments.of("-1", false),
                Arguments.of("", false),
                Arguments.of("abc", false)
        );
    }

    @ParameterizedTest
    @MethodSource("validateShipStaffProvider")
    void validateShipStaff(String staff, boolean expectedValid) {
        ShipValidator.validateShipStaff(staff, "validateShipStaff", errors);
        assertEquals(expectedValid, errors.isEmpty());
    }
}
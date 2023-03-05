package com.java.cruisecompany.model.entity.wrapper;

import com.java.cruisecompany.model.entity.Port;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * The Waypoint class represents a wrapper class for a Port entity.
 * It contains the following properties: port, arriveTime, departureTime.
 * It is used to represent a point of arrival or departure on a route.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, toString methods,
 * and to generate a builder for creating instances of the class.
 */
@Builder
@Data
public class Waypoint {
    Port port;
    LocalDate arriveTime;
    LocalDate departureTime;
}

package com.java.cruisecompany.model.entity;

import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The Route class represents a Route entity.
 * This class contains the following properties: id, name, startOfCruise, endOfCruise, duration, price, waypoints.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods,
 * and to generate a builder for creating instances of the class.
 */
@Builder
@Data
public class Route {
    private long id;
    private String name;
    private LocalDate startOfCruise;
    private LocalDate endOfCruise;
    private int duration;
    private BigDecimal price;
    private List<Waypoint> waypoints;

}

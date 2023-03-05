package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The RouteDTO class represents a Data Transfer Object for a Route
 * This class contains the following properties: id, name, startOfCruise, endOfCruise, price, waypoints, duration and
 * numOfPorts.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods.
 *
 * @see com.java.cruisecompany.model.entity.Route
 */
@Builder
@Data
public class RouteDTO {
    private long id;
    private String name;
    private LocalDate startOfCruise;
    private LocalDate endOfCruise;
    private BigDecimal price;
    private List<Waypoint> waypoints;
    private int duration;
    private long numOfPorts;
}

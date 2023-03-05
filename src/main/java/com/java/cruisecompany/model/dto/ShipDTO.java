package com.java.cruisecompany.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The ShipDTO class represents a Data Transfer Object for a Ship.
 * This class contains the following properties: id, name, capacity, visitedPorts, staff, imagePath, route.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods.
 */
@Builder
@Data
public class ShipDTO {
    private long id;
    private String name;
    private int capacity;
    private int visitedPorts;
    private int staff;
    String imagePath;
    private RouteDTO route;
}

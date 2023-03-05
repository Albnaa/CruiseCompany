package com.java.cruisecompany.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * The Ship class represents a Ship entity.
 * This class contains the following properties: id, name, capacity, visited_ports, staff, imagePath, route.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods,
 * and to generate a builder for creating instances of the class.
 */
@Builder
@Data
public class Ship {
    private long id;
    private String name;
    private int capacity;
    private int visited_ports;
    private int staff;
    String imagePath;
    private Route route;
}

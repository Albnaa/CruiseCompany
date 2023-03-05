package com.java.cruisecompany.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * The Port class represents a Port entity.
 * This class contains the following properties: id, name.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods,
 * and to generate a builder for creating instances of the class.
 */
@Builder
@Data
public class Port {
    private long id;
    private String name;
}

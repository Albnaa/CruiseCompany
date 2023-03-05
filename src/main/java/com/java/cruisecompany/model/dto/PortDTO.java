package com.java.cruisecompany.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The PortDTO class represents a Data Transfer Object for a Port.
 * This class contains the following properties: id, name.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods.
 */
@Builder
@Data
public class PortDTO {
    private long id;
    private String name;
}

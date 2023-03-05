package com.java.cruisecompany.model.entity;

import com.java.cruisecompany.model.entity.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The Ticket class represents a Ticket entity.
 * This class contains the following properties: id, passengersCount, price, documentPath, user, ship, status.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods,
 * and to generate a builder for creating instances of the class.
 */
@Builder
@Data
public class Ticket {
    private long id;
    private int passengersCount;
    private BigDecimal price;
    private String documentPath;
    private User user;
    private Ship ship;
    private Status status;

}

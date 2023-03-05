package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The TicketDTO class represents a Data Transfer Object for a Ticket.
 * This class contains the following properties: id, passengersCount, price, documentPath, status, user, ship.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods.
 */
@Builder
@Data
public class TicketDTO {
    private long id;
    private int passengersCount;
    private BigDecimal price;
    private String documentPath;
    private Status status;
    private UserDTO user;
    private ShipDTO ship;
}

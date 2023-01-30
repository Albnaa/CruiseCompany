package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.entity.enums.Status;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@Data
public class TicketDTO {
    private long id;
    private int passengersCount;
    private BigDecimal price;
    private Status status;
    private UserDTO user;
    private ShipDTO ship;
}

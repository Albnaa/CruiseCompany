package com.java.cruisecompany.model.entity;

import com.java.cruisecompany.model.entity.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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

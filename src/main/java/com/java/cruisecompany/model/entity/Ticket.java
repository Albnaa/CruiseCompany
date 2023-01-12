package com.java.cruisecompany.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
public class Ticket {
    private long id;
    private int passengers_count;
    private double price;
    private User user;
    private Ship ship;
    @EqualsAndHashCode.Exclude private int statusId;

}

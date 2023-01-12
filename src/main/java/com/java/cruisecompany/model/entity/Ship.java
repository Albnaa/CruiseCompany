package com.java.cruisecompany.model.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Ship {
    private long id;
    private String name;
    private int capacity;
    private int visited_ports;
    private int staff;
    private Route route;
}

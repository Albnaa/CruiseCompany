package com.app.webapplication.model.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Ship {
    private long id;
    private int capacity;
    private int visited_ports;
    private int staff;
}

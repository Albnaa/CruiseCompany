package com.app.webapplication.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Route {
    private long id;
    private String name;
    private LocalDate startOfCruise;
    private LocalDate endOfCruise;
    private Ship ship;
    private List<Port> ports;
}

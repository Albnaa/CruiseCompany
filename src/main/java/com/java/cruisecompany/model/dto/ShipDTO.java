package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.Route;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShipDTO {
    private long id;
    private String name;
    private int capacity;
    private int visitedPorts;
    private int staff;
    private RouteDTO route;
}

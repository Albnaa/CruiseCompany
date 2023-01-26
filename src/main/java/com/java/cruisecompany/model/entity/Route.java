package com.java.cruisecompany.model.entity;

import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Route {
    private long id;
    private String name;
    private LocalDate startOfCruise;
    private LocalDate endOfCruise;
    private BigDecimal price;
    private List<Waypoint> waypoints;

}

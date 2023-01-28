package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class RouteDTO {
    private long id;
    private String name;
    private LocalDate startOfCruise;
    private LocalDate endOfCruise;
    private BigDecimal price;
    private List<Waypoint> waypoints;
    private int duration;
    private long numOfPorts;
}

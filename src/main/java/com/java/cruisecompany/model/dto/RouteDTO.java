package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.wrapper.Waypoint;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class RouteDTO {
    private long id;
    private String name;
    private LocalDate startOfCruise;
    private LocalDate endOfCruise;
    private List<Waypoint> waypoints;
    private long duration;
    private long numOfPorts;
}

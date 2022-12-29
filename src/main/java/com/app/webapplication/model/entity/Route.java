package com.app.webapplication.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class Route {
    private long id;
    private String name;
    private LocalDateTime startOfCruise;
    private LocalDateTime endOfCruise;
    private Set<Port> ports;
}

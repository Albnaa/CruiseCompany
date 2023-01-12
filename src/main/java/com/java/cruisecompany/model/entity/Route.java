package com.java.cruisecompany.model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Builder
@Data
public class Route {
    private long id;
    private String name;
    private Date startOfCruise;
    private Date endOfCruise;
    private Set<Port> ports;
}

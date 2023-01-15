package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.Port;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Builder
@Data
public class RouteDTO {
    private long id;
    private String name;
    private Date startOfCruise;
    private Date endOfCruise;
    private Set<Port> ports;
}

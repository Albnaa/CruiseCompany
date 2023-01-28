package com.java.cruisecompany.model.entity.wrapper;

import com.java.cruisecompany.model.entity.Port;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
@Builder
@Data
public class Waypoint {
     Port port;
     LocalDate arriveTime;
     LocalDate departureTime;
}

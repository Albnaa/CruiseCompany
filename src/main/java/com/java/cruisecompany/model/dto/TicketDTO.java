package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.Ship;
import com.java.cruisecompany.model.entity.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
public class TicketDTO {
    private long id;
    private int passengers_count;
    private double price;
    private UserDTO user;
    private Ship ship;
    @EqualsAndHashCode.Exclude private int statusId;
}

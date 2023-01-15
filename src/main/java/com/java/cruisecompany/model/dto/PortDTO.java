package com.java.cruisecompany.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PortDTO {
    private long id;
    private String name;
}

package com.java.cruisecompany.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Port {
    private long id;
    private String name;
}

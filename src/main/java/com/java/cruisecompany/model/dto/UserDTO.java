package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@Data
public class UserDTO {
    private long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private BigDecimal balance;
}

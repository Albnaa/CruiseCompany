package com.java.cruisecompany.model.dto;

import com.java.cruisecompany.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The UserDTO class represents a Data Transfer Object for a User.
 * This class contains the following properties: id, login, email, firstName, lastName, role, balance.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods.
 */
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

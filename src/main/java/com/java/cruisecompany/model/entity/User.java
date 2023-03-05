package com.java.cruisecompany.model.entity;

import com.java.cruisecompany.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The User class represents a User entity.
 * This class contains the following properties: id, login, email, password, firstName, lastName, role, balance.
 * It uses Lombok annotations to generate boilerplate code for getters, setters, equals, hashCode, and toString methods,
 * and to generate a builder for creating instances of the class.
 */
@Builder
@Data
public class User {
    private long id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private BigDecimal balance;
}

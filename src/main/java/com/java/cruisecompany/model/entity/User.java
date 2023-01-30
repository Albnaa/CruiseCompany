package com.java.cruisecompany.model.entity;

import com.java.cruisecompany.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private double balance;
}

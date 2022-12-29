package com.app.webapplication.model.entity;

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
    private String first_name;
    private String last_name;
    @EqualsAndHashCode.Exclude private int roleId;
}

package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.entity.UserDTO;

public class MapperDTO {
    private MapperDTO() {};

    public static UserDTO mapUserToDTO(UserDTO user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }

    public static UserDTO mapDTOtoUser(com.java.cruisecompany.model.dto.UserDTO userDTO) {
        return UserDTO.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .role(userDTO.getRole())
                .build();
    }
}

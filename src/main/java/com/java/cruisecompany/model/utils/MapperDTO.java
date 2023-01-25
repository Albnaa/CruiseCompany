package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.User;

public class MapperDTO {
    private MapperDTO() {}

    public static UserDTO mapUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .balance(user.getBalance())
                .build();
    }

    public static User mapDTOtoUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .role(userDTO.getRole())
                .balance(userDTO.getBalance())
                .build();
    }

    public static PortDTO mapPortToDTO(Port port) {
        return PortDTO.builder()
                .id(port.getId())
                .name(port.getName())
                .build();
    }

    public static Port mapDTOtoPort(PortDTO portDTO) {
        return Port.builder()
                .id(portDTO.getId())
                .name(portDTO.getName())
                .build();
    }

    public static RouteDTO mapRouteToDTO(Route route) {
        return RouteDTO.builder()
                .id(route.getId())
                .name(route.getName())
                .startOfCruise(route.getStartOfCruise())
                .endOfCruise(route.getEndOfCruise())
                .build();
    }

    public static Route mapDTOtoRoute(RouteDTO routeDTO) {
        return Route.builder()
                .id(routeDTO.getId())
                .name(routeDTO.getName())
                .startOfCruise(routeDTO.getStartOfCruise())
                .endOfCruise(routeDTO.getEndOfCruise())
                .build();
    }
}

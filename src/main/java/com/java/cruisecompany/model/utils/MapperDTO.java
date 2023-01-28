package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.dto.PortDTO;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.entity.Route;
import com.java.cruisecompany.model.entity.Ship;
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
                .duration(route.getDuration())
                .price(route.getPrice())
                .waypoints(route.getWaypoints())
                .build();
    }

    public static Route mapDTOtoRoute(RouteDTO routeDTO) {
        return Route.builder()
                .id(routeDTO.getId())
                .name(routeDTO.getName())
                .startOfCruise(routeDTO.getStartOfCruise())
                .endOfCruise(routeDTO.getEndOfCruise())
                .duration(routeDTO.getDuration())
                .price(routeDTO.getPrice())
                .waypoints(routeDTO.getWaypoints())
                .build();
    }

    public static ShipDTO mapShipToDTO(Ship ship) {
        if (ship.getRoute() != null) {
            return ShipDTO.builder()
                    .id(ship.getId())
                    .name(ship.getName())
                    .capacity(ship.getCapacity())
                    .visitedPorts(ship.getVisited_ports())
                    .staff(ship.getStaff())
                    .route(mapRouteToDTO(ship.getRoute()))
                    .build();
        } else {
            return ShipDTO.builder()
                    .id(ship.getId())
                    .name(ship.getName())
                    .capacity(ship.getCapacity())
                    .visitedPorts(ship.getVisited_ports())
                    .staff(ship.getStaff())
                    .build();
        }
    }

    public static Ship mapDTOToShip(ShipDTO shipDTO) {
        if (shipDTO.getRoute() != null) {
            return Ship.builder()
                    .id(shipDTO.getId())
                    .name(shipDTO.getName())
                    .capacity(shipDTO.getCapacity())
                    .visited_ports(shipDTO.getVisitedPorts())
                    .staff(shipDTO.getStaff())
                    .route(mapDTOtoRoute(shipDTO.getRoute()))
                    .build();
        } else {
            return Ship.builder()
                    .id(shipDTO.getId())
                    .name(shipDTO.getName())
                    .capacity(shipDTO.getCapacity())
                    .visited_ports(shipDTO.getVisitedPorts())
                    .staff(shipDTO.getStaff())
                    .build();
        }
    }
}

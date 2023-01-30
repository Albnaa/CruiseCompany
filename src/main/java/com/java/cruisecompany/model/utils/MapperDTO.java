package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.dto.*;
import com.java.cruisecompany.model.entity.*;

public class MapperDTO {
    private MapperDTO() {
    }

    public static UserDTO mapUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .balance(user.getBalance())
                .role(user.getRole())
                .build();
    }

    public static User mapDTOtoUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .balance(userDTO.getBalance())
                .role(userDTO.getRole())
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

    public static TicketDTO mapTicketToDTO(Ticket ticket) {
        if (ticket.getUser() != null && ticket.getShip() != null) {
            return TicketDTO.builder()
                    .id(ticket.getId())
                    .passengersCount(ticket.getPassengersCount())
                    .price(ticket.getPrice())
                    .status(ticket.getStatus())
                    .user(mapUserToDTO(ticket.getUser()))
                    .ship(mapShipToDTO(ticket.getShip()))
                    .build();
        } else if (ticket.getUser() != null) {
            return TicketDTO.builder()
                    .id(ticket.getId())
                    .passengersCount(ticket.getPassengersCount())
                    .price(ticket.getPrice())
                    .status(ticket.getStatus())
                    .user(mapUserToDTO(ticket.getUser()))
                    .build();
        } else if (ticket.getShip() != null) {
            return TicketDTO.builder()
                    .id(ticket.getId())
                    .passengersCount(ticket.getPassengersCount())
                    .price(ticket.getPrice())
                    .status(ticket.getStatus())
                    .ship(mapShipToDTO(ticket.getShip()))
                    .build();
        } else {
            return TicketDTO.builder()
                    .id(ticket.getId())
                    .passengersCount(ticket.getPassengersCount())
                    .price(ticket.getPrice())
                    .status(ticket.getStatus())
                    .build();
        }

    }

    public static Ticket mapDTOToTicket(TicketDTO ticketDTO) {
        if (ticketDTO.getUser() != null && ticketDTO.getShip() != null) {
            return Ticket.builder()
                    .id(ticketDTO.getId())
                    .passengersCount(ticketDTO.getPassengersCount())
                    .price(ticketDTO.getPrice())
                    .status(ticketDTO.getStatus())
                    .user(mapDTOtoUser(ticketDTO.getUser()))
                    .ship(mapDTOToShip(ticketDTO.getShip()))
                    .build();
        } else if (ticketDTO.getUser() != null) {
            return Ticket.builder()
                    .id(ticketDTO.getId())
                    .passengersCount(ticketDTO.getPassengersCount())
                    .price(ticketDTO.getPrice())
                    .status(ticketDTO.getStatus())
                    .user(mapDTOtoUser(ticketDTO.getUser()))
                    .build();
        } else if (ticketDTO.getShip() != null) {
            return Ticket.builder()
                    .id(ticketDTO.getId())
                    .passengersCount(ticketDTO.getPassengersCount())
                    .price(ticketDTO.getPrice())
                    .status(ticketDTO.getStatus())
                    .ship(mapDTOToShip(ticketDTO.getShip()))
                    .build();
        } else {
            return Ticket.builder()
                    .id(ticketDTO.getId())
                    .passengersCount(ticketDTO.getPassengersCount())
                    .price(ticketDTO.getPrice())
                    .status(ticketDTO.getStatus())
                    .build();
        }
    }

}

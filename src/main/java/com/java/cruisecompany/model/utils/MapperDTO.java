package com.java.cruisecompany.model.utils;

import com.java.cruisecompany.model.dto.*;
import com.java.cruisecompany.model.entity.*;

/**
 * The MapperDTO class provides static methods for converting domain objects to DTO objects and vice versa.
 */
public class MapperDTO {
    /**
     * Private constructor to prevent instantiation.
     */
    private MapperDTO() {
    }

    /**
     * Maps a Port object to a PortDTO object.
     *
     * @param port the Port object to map.
     * @return the PortDTO object.
     */
    public static PortDTO mapPortToDTO(Port port) {
        return PortDTO.builder()
                .id(port.getId())
                .name(port.getName())
                .build();
    }

    /**
     * Maps a PortDTO object to a Port object.
     *
     * @param portDTO the PortDTO object to map.
     * @return the Port object.
     */
    public static Port mapDTOtoPort(PortDTO portDTO) {
        return Port.builder()
                .id(portDTO.getId())
                .name(portDTO.getName())
                .build();
    }

    /**
     * Maps a User object to a UserDTO object.
     *
     * @param user the User object to map.
     * @return the UserDTO object.
     */
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

    /**
     * Maps a UserDTO object to a User object.
     *
     * @param userDTO the UserDTO object to map.
     * @return the User object.
     */
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

    /**
     * Maps a Route object to a RouteDTO object.
     *
     * @param route the Route object to map.
     * @return the RouteDTO object.
     */
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

    /**
     * Maps a RouteDTO object to a Route object.
     *
     * @param routeDTO the RouteDTO object to map.
     * @return the Route object.
     */
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

    /**
     * Maps a Ship object to a ShipDTO object.
     *
     * @param ship the Ship object to map.
     * @return the ShipDTO object.
     */
    public static ShipDTO mapShipToDTO(Ship ship) {
        ShipDTO.ShipDTOBuilder builder = ShipDTO.builder()
                .id(ship.getId())
                .name(ship.getName())
                .capacity(ship.getCapacity())
                .visitedPorts(ship.getVisited_ports())
                .staff(ship.getStaff())
                .imagePath(ship.getImagePath());

        if (ship.getRoute() != null) {
            builder.route(mapRouteToDTO(ship.getRoute()));
        }

        return builder.build();
    }

    /**
     * Maps a ShipDTO object to a Ship object.
     *
     * @param shipDTO the ShipDTO object to map.
     * @return the Ship object.
     */
    public static Ship mapDTOToShip(ShipDTO shipDTO) {
        Ship.ShipBuilder builder = Ship.builder()
                .id(shipDTO.getId())
                .name(shipDTO.getName())
                .capacity(shipDTO.getCapacity())
                .visited_ports(shipDTO.getVisitedPorts())
                .staff(shipDTO.getStaff())
                .imagePath(shipDTO.getImagePath());

        if (shipDTO.getRoute() != null) {
            builder.route(mapDTOtoRoute(shipDTO.getRoute()));
        }

        return builder.build();
    }

    /**
     * Maps a Ticket object to a TicketDTO object.
     *
     * @param ticket the Ticket object to map.
     * @return the TicketDTO object.
     */
    public static TicketDTO mapTicketToDTO(Ticket ticket) {
        TicketDTO.TicketDTOBuilder builder = TicketDTO.builder()
                .id(ticket.getId())
                .passengersCount(ticket.getPassengersCount())
                .price(ticket.getPrice())
                .status(ticket.getStatus())
                .documentPath(ticket.getDocumentPath());

        if (ticket.getUser() != null) {
            builder.user(mapUserToDTO(ticket.getUser()));
        }

        if (ticket.getShip() != null) {
            builder.ship(mapShipToDTO(ticket.getShip()));
        }

        return builder.build();
    }

    /**
     * Maps a TicketDTO object to a Ticket object.
     *
     * @param ticketDTO the TicketDTO object to map.
     * @return the Ticket object.
     */
    public static Ticket mapDTOToTicket(TicketDTO ticketDTO) {
        Ticket.TicketBuilder builder = Ticket.builder()
                .id(ticketDTO.getId())
                .passengersCount(ticketDTO.getPassengersCount())
                .price(ticketDTO.getPrice())
                .status(ticketDTO.getStatus())
                .documentPath(ticketDTO.getDocumentPath());

        if (ticketDTO.getUser() != null) {
            builder.user(mapDTOtoUser(ticketDTO.getUser()));
        }

        if (ticketDTO.getShip() != null) {
            builder.ship(mapDTOToShip(ticketDTO.getShip()));
        }

        return builder.build();
    }
}

package com.java.cruisecompany.model.dao.constant;

/**
 * This interface provides constants for the table field names used in the application database.
 */
public interface TableFields {
    String PORT_ID = "port.id";
    String PORT_NAME = "port.name";

    String ROUTE_ID = "route.id";
    String ROUTE_NAME = "route.name";
    String ROUTE_START_OF_CRUISE = "route.start_of_cruise";
    String ROUTE_END_OF_CRUISE = "route.end_of_cruise";
    String ROUTE_DURATION = "route.duration";
    String ROUTE_PRICE = "route.price";

    String ROUTE_HAS_PORT_ARRIVE_TIME = "route_has_port.arrive_time";
    String ROUTE_HAS_PORT_DEPARTURE_TIME = "route_has_port.departure_time";

    String SHIP_ID = "ship.id";
    String SHIP_NAME = "ship.name";
    String SHIP_CAPACITY = "ship.capacity";
    String SHIP_VISITED_PORTS = "ship.visited_ports";
    String SHIP_STAFF = "ship.staff";
    String SHIP_IMAGE_PATH = "ship.image_path";

    String TICKET_ID = "ticket.id";
    String TICKET_PASSENGERS_COUNT = "ticket.passengers_count";
    String TICKET_PRICE = "ticket.price";
    String TICKET_DOCUMENT_PATH = "ticket.document_path";
    String TICKET_STATUS_ID = "ticket.status_id";
    String TICKET_SHIP_ID = "ticket.ship_id";

    String USER_ID = "user.id";
    String USER_LOGIN = "user.login";
    String USER_EMAIL = "user.email";
    String USER_PASSWORD = "user.password";
    String USER_FIRST_NAME = "user.first_name";
    String USER_LAST_NAME = "user.last_name";
    String USER_BALANCE = "user.balance";
    String USER_ROLE_ID = "user.role_id";
}

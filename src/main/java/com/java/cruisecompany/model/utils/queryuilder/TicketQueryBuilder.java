package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class TicketQueryBuilder extends QueryBuilder{
    private static final List<String> TICKET_FIELDS = new ArrayList<>();
    private final List<String> filterList = new ArrayList<>();

    static {
        TICKET_FIELDS.add("ticket.id");
        TICKET_FIELDS.add("ticket.passengers_count");
        TICKET_FIELDS.add("ticket.price");
        TICKET_FIELDS.add("ticket.status_id");
        TICKET_FIELDS.add("route.name");
        TICKET_FIELDS.add("route.start_of_cruise");
        TICKET_FIELDS.add("user.first_name");
        TICKET_FIELDS.add("user.last_name");
        TICKET_FIELDS.add("ship.name");
        TICKET_FIELDS.add("route.name");
    }
    @Override
    String buildGroupByFragment() {
        return " GROUP BY ticket.id, ticket.passengers_count, ticket.price, ticket.status_id, user.id, user.first_name," +
                " user.last_name, user.balance, ticket.ship_id, ship.name, r.id, r.name, r.start_of_cruise ";
    }

    @Override
    String buildFilterFragment() {
        if (filterList.isEmpty()) {
            return "";
        }
        String result = " WHERE ";
        result += String.join(" AND ", filterList);
        return result;
    }

    private void setUserFilter(String parameter) {
        try {
            int value = Integer.parseInt(parameter);
            if (value >= 1) {
                filterList.add("user.id = " + value);
            }
        } catch (NumberFormatException e) {
            return;
        }
    }

    @Override
    void extractFilterParameters(HttpServletRequest request) {
        setUserFilter(request.getParameter("userF"));
    }

    @Override
    boolean parameterIsValid(String parameter) {
        return false;
    }

    @Override
    String getDefaultSort() {
        return TICKET_FIELDS.get(0);
    }
}

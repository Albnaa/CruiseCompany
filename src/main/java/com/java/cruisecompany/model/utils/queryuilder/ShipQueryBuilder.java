package com.java.cruisecompany.model.utils.queryuilder;

import com.java.cruisecompany.exceptions.InvalidInputException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class ShipQueryBuilder extends QueryBuilder {
    private static final List<String> SHIP_FIELDS = new ArrayList<>();
    private final List<String> filterList = new ArrayList<>();

    static {
        SHIP_FIELDS.add("ship.id");
        SHIP_FIELDS.add("ship.name");
        SHIP_FIELDS.add("ship.capacity");
        SHIP_FIELDS.add("ship.visited_ports");
        SHIP_FIELDS.add("ship.staff");
        SHIP_FIELDS.add("ship.route_id");
        SHIP_FIELDS.add("route.name");
        SHIP_FIELDS.add("route.start_of_cruise");
        SHIP_FIELDS.add("route.end_of_cruise");
        SHIP_FIELDS.add("route.duration");
        SHIP_FIELDS.add("route.price");
    }

    @Override
    String buildGroupByFragment() {
        return " GROUP BY ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff, route.id, route.name, " +
                "route.start_of_cruise, route.end_of_cruise, route.price ";
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

    private void setDurationFilter(String parameter) {
        try {
            int value = Integer.parseInt(parameter);
            if (value >= 3 && value <= 31) {
                filterList.add("route.duration = " + value);
            }
        } catch (NumberFormatException e) {
            return;
        }
    }

    private void setNameFilter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            filterList.add("route.name LIKE '%" + parameter + "%'");
        }
    }

    private void setStartDateFilter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            filterList.add("route.start_of_cruise = '" + parameter + "'");
        }
    }

    @Override
    void extractFilterParameters(HttpServletRequest request) throws InvalidInputException {
        setDurationFilter(request.getParameter("durationF"));
        setNameFilter(request.getParameter("nameF"));
        setStartDateFilter(request.getParameter("startDateF"));
    }

    @Override
    boolean parameterIsValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) return false;
        return SHIP_FIELDS.contains(parameter);
    }

    @Override
    String getDefaultSort() {
        return SHIP_FIELDS.get(0);
    }
}

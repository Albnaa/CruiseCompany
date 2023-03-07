package com.java.cruisecompany.model.utils.queryuilder;

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

    /**
     * {@inheritDoc}
     */
    @Override
    String buildGroupByFragment() {
        return " GROUP BY ship.id, ship.name, ship.capacity, ship.visited_ports, ship.staff, route.id, route.name, " +
                "route.start_of_cruise, route.end_of_cruise, route.price ";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String buildFilterFragment() {
        if (filterList.isEmpty()) {
            return "";
        }
        String result = " WHERE ";
        result += String.join(" AND ", filterList);
        return result;
    }

    /**
     * Adds a filter for the duration of the route.
     *
     * @param parameter a string representing the duration to be searched.
     */
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

    /**
     * Adds a filter for the name of the ship.
     *
     * @param parameter a string representing the name to be searched.
     */
    private void setNameFilter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            filterList.add("route.name LIKE '%" + parameter + "%'");
        }
    }

    /**
     * Adds a filter for the start date of the cruise.
     *
     * @param parameter a string representing the start date to be searched.
     */
    private void setStartDateFilter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            filterList.add("route.start_of_cruise = '" + parameter + "'");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param request HTTP servlet request
     */
    @Override
    void extractFilterParameters(HttpServletRequest request) {
        setDurationFilter(request.getParameter("durationF"));
        setNameFilter(request.getParameter("nameF"));
        setStartDateFilter(request.getParameter("startDateF"));
    }

    /**
     * {@inheritDoc}
     *
     * @param parameter parameter to validate
     */
    @Override
    boolean isValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) return false;
        return SHIP_FIELDS.contains(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getDefaultSort() {
        return SHIP_FIELDS.get(0);
    }
}

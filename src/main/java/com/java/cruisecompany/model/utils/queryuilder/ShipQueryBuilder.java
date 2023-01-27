package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class ShipQueryBuilder extends QueryBuilder{
    private static final List<String> SHIP_FIELDS = new ArrayList<>();
    private final List<String> filterList = new ArrayList<>();

    static {
        SHIP_FIELDS.add("ship.id");
        SHIP_FIELDS.add("ship.name");
        SHIP_FIELDS.add("ship.capacity");
        SHIP_FIELDS.add("ship.visited_ports");
        SHIP_FIELDS.add("ship.staff");
        SHIP_FIELDS.add("ship.route_id");
    }
    @Override
    String buildGroupByFragment() {
        return " GROUP BY" + SHIP_FIELDS.get(0);
    }

    @Override
    String buildFilterFragment() {
        return null;
    }

    @Override
    void extractFilterParameters(HttpServletRequest request) {

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

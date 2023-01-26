package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class RouteQueryBuilder extends QueryBuilder{
    private static final List<String> ROUTE_FIELDS = new ArrayList<>();
    private final List<String> filterList = new ArrayList<>();

    static {
        ROUTE_FIELDS.add("r.id");
        ROUTE_FIELDS.add("r.name");
        ROUTE_FIELDS.add("r.start_of_cruise");
        ROUTE_FIELDS.add("r.end_of_cruise");
    }
    @Override
    String buildGroupByFragment() {
        return " GROUP BY " + ROUTE_FIELDS.get(0) + ", p.id, rhp.arrive_time, rhp.departure_time";
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

    @Override
    void extractFilterParameters(HttpServletRequest request) {

    }

    @Override
    boolean parameterIsValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) return false;
        return ROUTE_FIELDS.contains(parameter);
    }

    @Override
    String getDefaultSort() {
        return ROUTE_FIELDS.get(0);
    }
}
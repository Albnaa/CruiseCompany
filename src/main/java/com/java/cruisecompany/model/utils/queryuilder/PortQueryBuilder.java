package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class PortQueryBuilder extends QueryBuilder{
    private static final List<String> PORT_FIELDS = new ArrayList<>();

    static {
        PORT_FIELDS.add("port.id");
        PORT_FIELDS.add("port.name");
    }

    @Override
    String buildGroupByFragment() {
        return " GROUP BY " + PORT_FIELDS.get(0);
    }

    @Override
    String buildFilterFragment() {
        return "";
    }

    @Override
    void extractFilterParameters(HttpServletRequest request) {
    }

    @Override
    boolean parameterIsValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return false;
        }
        return PORT_FIELDS.contains(parameter);
    }

    @Override
    String getDefaultSort() {
        return PORT_FIELDS.get(0);
    }
}

package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class PortQueryBuilder extends QueryBuilder{
    private static final List<String> PORT_FIELDS = new ArrayList<>();
    private final List<String> FILTER_LIST = new ArrayList<>();

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
        if (FILTER_LIST.isEmpty()) {
            return "";
        }
        String result = " WHERE ";
        result += String.join(" AND ", FILTER_LIST);
        return result;
    }

    private void setNameFilter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            FILTER_LIST.add("port.name LIKE '%" + parameter + "%'");
        }
    }

    @Override
    void extractFilterParameters(HttpServletRequest request) {
        setNameFilter(request.getParameter("nameF"));
    }

    @Override
    boolean isValid(String parameter) {
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

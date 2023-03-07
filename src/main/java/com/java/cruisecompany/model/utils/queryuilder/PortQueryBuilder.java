package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class PortQueryBuilder extends QueryBuilder {
    private static final List<String> PORT_FIELDS = new ArrayList<>();
    private final List<String> FILTER_LIST = new ArrayList<>();

    static {
        PORT_FIELDS.add("port.id");
        PORT_FIELDS.add("port.name");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String buildGroupByFragment() {
        return " GROUP BY " + PORT_FIELDS.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String buildFilterFragment() {
        if (FILTER_LIST.isEmpty()) {
            return "";
        }
        String result = " WHERE ";
        result += String.join(" AND ", FILTER_LIST);
        return result;
    }

    /**
     * Adds a filter for the name of the port.
     *
     * @param parameter a string representing the name to be searched.
     */
    private void setNameFilter(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            FILTER_LIST.add("port.name LIKE '%" + parameter + "%'");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param request HTTP servlet request
     */
    @Override
    void extractFilterParameters(HttpServletRequest request) {
        setNameFilter(request.getParameter("nameF"));
    }

    /**
     * {@inheritDoc}
     *
     * @param parameter parameter to validate
     */
    @Override
    boolean isValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return false;
        }
        return PORT_FIELDS.contains(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getDefaultSort() {
        return PORT_FIELDS.get(0);
    }
}

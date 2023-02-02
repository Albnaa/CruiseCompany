package com.java.cruisecompany.model.utils.queryuilder;

import com.java.cruisecompany.exceptions.InvalidInputException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

public abstract class QueryBuilder {
    private String sort;
    private String order = "ASC";
    private int offset;
    private int rows;

    private void setSort(String parameter) {
        if (parameterIsValid(parameter)) {
            sort = parameter;
        } else {
            sort = getDefaultSort();
        }
    }

    private void setOrder(String parameter) {
        if (Objects.equals(parameter, "desc")) {
            order = parameter;
        }
    }

    private void setOffset(String parameter) {
        offset = parseIntFromParam(parameter, 0, 0);
    }

    private void setRows(String parameter) {
        rows = parseIntFromParam(parameter, 1, 5);
    }

    private String buildSortFragment() {
        return " ORDER BY " + sort + " " + order;
    }


    private String buildPaginationFragment() {
        return " LIMIT " + rows + " OFFSET " + offset;
    }

    public String buildQuery() {
        return buildFilterFragment() + buildGroupByFragment() + buildSortFragment() + buildPaginationFragment();
    }

    public String buildFilterQuery() {
        return buildFilterFragment();
    }

    public void extractBuilderParameters(HttpServletRequest request) throws InvalidInputException {
        setSort(request.getParameter("sort"));
        setOrder(request.getParameter("order"));
        setOffset(request.getParameter("offset"));
        setRows(request.getParameter("rows"));

        extractFilterParameters(request);
    }

    private int parseIntFromParam(String parameter, int minValue, int defaultValue) {
        try {
            int value = Integer.parseInt(parameter);
            return value >= minValue ? value : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    abstract String buildGroupByFragment();
    abstract String buildFilterFragment();
    abstract void extractFilterParameters(HttpServletRequest request) throws InvalidInputException;
    abstract boolean parameterIsValid(String parameter);
    abstract String getDefaultSort();
}
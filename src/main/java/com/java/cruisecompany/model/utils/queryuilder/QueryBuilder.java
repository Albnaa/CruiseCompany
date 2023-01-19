package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

public abstract class QueryBuilder {
    private String sort;
    private String order = "ASC";

    private int offset = 0;
    private int rows = 5;

    //param = ?sort=balance&order=asc

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
        if (parameter != null && Integer.parseInt(parameter) > -1) {
            offset = Integer.parseInt(parameter);
        }
    }

    private void setRows(String parameter) {
        if (parameter != null && Integer.parseInt(parameter) > -1) {
            rows = Integer.parseInt(parameter);
        }
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
        System.out.println(buildFilterFragment());
        return buildFilterFragment();
    }

    public void extractBuilderParameters(HttpServletRequest request) {
        setSort(request.getParameter("sort"));
        setOrder(request.getParameter("order"));
        setOffset(request.getParameter("offset"));
        setRows(request.getParameter("rows"));

        extractFilterParameters(request);
    }
    abstract String buildGroupByFragment();
    abstract String buildFilterFragment();
    abstract void extractFilterParameters(HttpServletRequest request);
    abstract boolean parameterIsValid(String parameter);
    abstract String getDefaultSort();
}
// extract param from req
// pass param to the set methods
// call build method ??
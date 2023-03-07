package com.java.cruisecompany.model.utils.queryuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

/**
 * QueryBuilder is an abstract class that builds SQL queries with filtering, sorting, and pagination.
 * <p>
 * It defines several methods to set and retrieve query parameters and to build the corresponding SQL statements.
 * <p>
 * It also defines abstract methods to be implemented by its inheritors to specify the filtering and grouping statements
 * for their respective models.
 */
public abstract class QueryBuilder {
    private String sort;
    private String order = "ASC";
    private int offset;
    private int rows;

    /**
     * Sets the sort parameter for the query.
     *
     * @param parameter the sort parameter to set.
     */
    void setSort(String parameter) {
        if (isValid(parameter)) {
            sort = parameter;
        } else {
            sort = getDefaultSort();
        }
    }

    /**
     * Sets the order parameter for the query.
     *
     * @param parameter the order parameter to set.
     */
    void setOrder(String parameter) {
        if (Objects.equals(parameter, "desc")) {
            order = parameter;
        }
    }

    /**
     * Sets the offset parameter for the query.
     *
     * @param parameter the offset parameter to set.
     */
    private void setOffset(String parameter) {
        offset = parseIntFromParam(parameter, 0, 0);
    }

    /**
     * Sets the rows parameter for the query.
     *
     * @param parameter the rows parameter to set.
     */
    private void setRows(String parameter) {
        rows = parseIntFromParam(parameter, 1, 5);
    }

    /**
     * Builds the sorting fragment of the query.
     *
     * @return the sorting fragment of the query as a string.
     */
    private String buildSortFragment() {
        return " ORDER BY " + sort + " " + order;
    }


    /**
     * Builds the pagination fragment of the query.
     *
     * @return the pagination fragment of the query as a string.
     */
    private String buildPaginationFragment() {
        return " LIMIT " + rows + " OFFSET " + offset;
    }

    /**
     * Builds the full query string.
     *
     * @return the full query string as a string.
     */
    public String buildQuery() {
        return buildFilterFragment() + buildGroupByFragment() + buildSortFragment() + buildPaginationFragment();
    }

    /**
     * Builds the filter query string.
     *
     * @return the filter query string as a string.
     */
    public String buildFilterQuery() {
        return buildFilterFragment();
    }

    /**
     * Extracts the builder parameters from a HTTP servlet request.
     *
     * @param request the HTTP servlet request to extract the parameters from.
     */
    public void extractBuilderParameters(HttpServletRequest request) {
        setSort(request.getParameter("sort"));
        setOrder(request.getParameter("order"));
        setOffset(request.getParameter("offset"));
        setRows(request.getParameter("rows"));

        extractFilterParameters(request);
    }

    /**
     * Parses an integer parameter from a string with a minimum value and default value.
     *
     * @param parameter    the parameter string to parse.
     * @param minValue     the minimum value for the parameter.
     * @param defaultValue the default value for the parameter.
     * @return the parsed integer value.
     */
    private int parseIntFromParam(String parameter, int minValue, int defaultValue) {
        try {
            int value = Integer.parseInt(parameter);
            return value >= minValue ? value : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Builds the GROUP BY fragment of the query.
     *
     * @return the GROUP BY fragment as a string.
     */
    abstract String buildGroupByFragment();

    /**
     * Builds the Filter fragment of the query.
     *
     * @return the Filter fragment as a string.
     */
    abstract String buildFilterFragment();

    /**
     * Extracts the parameters related to filtering from the request.
     *
     * @param request HTTP servlet request
     */
    abstract void extractFilterParameters(HttpServletRequest request);

    /**
     * Validates whether a given parameter is valid for sorting.
     *
     * @param parameter parameter to validate
     */
    abstract boolean isValid(String parameter);

    /**
     * Returns the default sorting parameter when an invalid parameter is provided.
     */
    abstract String getDefaultSort();
}
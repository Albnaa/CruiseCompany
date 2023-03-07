package com.java.cruisecompany.model.utils.queryuilder;

import com.java.cruisecompany.model.entity.enums.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserQueryBuilder extends QueryBuilder {
    private static final List<String> USER_FIELDS = new ArrayList<>();
    private final List<String> filterList = new ArrayList<>();

    static {
        USER_FIELDS.add("user.id");
        USER_FIELDS.add("user.login");
        USER_FIELDS.add("user.email");
        USER_FIELDS.add("user.first_name");
        USER_FIELDS.add("user.last_name");
        USER_FIELDS.add("user.role_id");
        USER_FIELDS.add("user.balance");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    String buildGroupByFragment() {
        return " GROUP BY " + USER_FIELDS.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildFilterFragment() {
        if (filterList.isEmpty()) {
            return "";
        }
        String result = " WHERE ";
        result += String.join(" AND ", filterList);
        return result;
    }

    /**
     * Adds a filter for the role of a user.
     *
     * @param parameter a string representing the role to be searched.
     */
    void setRoleFilter(String parameter) {
        if (Objects.equals(parameter, "ADMIN") || Objects.equals(parameter, "USER")) {
            filterList.add("role_id = " + Role.parse(parameter).getIndex());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param request HTTP servlet request
     */
    @Override
    void extractFilterParameters(HttpServletRequest request) {
        setRoleFilter(request.getParameter("roleF"));
    }

    /**
     * {@inheritDoc}
     *
     * @param parameter parameter to validate
     */
    @Override
    boolean isValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) return false;
        return USER_FIELDS.contains(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getDefaultSort() {
        return USER_FIELDS.get(0);
    }
}

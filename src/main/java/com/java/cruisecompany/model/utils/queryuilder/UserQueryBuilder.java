package com.java.cruisecompany.model.utils.queryuilder;

import com.java.cruisecompany.model.entity.enums.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserQueryBuilder extends QueryBuilder{
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


    @Override
    String buildGroupByFragment() {
        return " GROUP BY " + USER_FIELDS.get(0);
    }

    @Override
    public String buildFilterFragment() {
        if (filterList.isEmpty()) {
            return "";
        }
        String result = " WHERE ";
        result += String.join(" AND ", filterList);
        return result;
    }

    void setRoleFilter(String parameter) {
        if (Objects.equals(parameter, "ADMIN") || Objects.equals(parameter, "USER")) {
            filterList.add("role_id = " + Role.getRoleFromString(parameter).getIndex());
        }
    }

    void setBalanceFilter(String parameter) {}

    @Override
    void extractFilterParameters(HttpServletRequest request) {
        setRoleFilter(request.getParameter("roleF"));
    }

    @Override
    boolean parameterIsValid(String parameter) {
        if (parameter == null || parameter.isEmpty()) return false;
        return USER_FIELDS.contains(parameter);
    }

    @Override
    String getDefaultSort() {
        return USER_FIELDS.get(0);
    }
}

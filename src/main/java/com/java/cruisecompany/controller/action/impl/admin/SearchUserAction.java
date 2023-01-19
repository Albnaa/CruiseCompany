package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.UserQueryBuilder;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class SearchUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) {
        QueryBuilder queryBuilder = new UserQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        System.out.println(queryBuilder.buildQuery());
        request.setAttribute("users", userService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, userService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "users.jsp";
    }
}

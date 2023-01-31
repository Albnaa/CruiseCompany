package com.java.cruisecompany.controller.action.impl.admin.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.UserQueryBuilder;
import jakarta.servlet.http.HttpServletRequest;

public class ManageUsersAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        QueryBuilder queryBuilder = new UserQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("users", userService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, userService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/user/manageUsers.jsp";
    }
}

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
import jakarta.servlet.http.HttpServletResponse;

/**
 * Action that handles the management of users. Retrieves a list of users from the UserService,
 * <p>
 * sorts them using the provided query parameters, and sets the resulting list as an attribute of the
 * <p>
 * request. Calculates the number of pages required for pagination and sets relevant attributes for the view.
 */
public class ManageUsersAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the action to delete a user from the system.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return a string representing the URL of the next page to display after the action is executed
     * @exception ServiceException  if an error occurs while executing the action
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new UserQueryBuilder();
        queryBuilder.extractBuilderParameters(request);
        SessionAttributeHandlerUtil.setAttrFromReqToSession(request);
        request.setAttribute("users", userService.findSorted(queryBuilder.buildQuery()));
        Pagination.calculatePages(request, userService.getNumOfRows(queryBuilder.buildFilterQuery()));
        return "/WEB-INF/jsp/user/manageUsers.jsp";
    }
}

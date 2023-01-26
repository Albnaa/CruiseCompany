package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.PortService;
import com.java.cruisecompany.model.utils.Pagination;
import com.java.cruisecompany.model.utils.SessionAttributeHandlerUtil;
import com.java.cruisecompany.model.utils.queryuilder.PortQueryBuilder;
import com.java.cruisecompany.model.utils.queryuilder.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;

public class ListPortAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        return (String) request.getSession().getAttribute("previousUrl");
    }
}

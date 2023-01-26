package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.controller.action.impl.admin.port.CreatePortAction;
import com.java.cruisecompany.controller.action.impl.admin.port.DeletePortAction;
import com.java.cruisecompany.controller.action.impl.admin.port.ManagePortAction;
import com.java.cruisecompany.controller.action.impl.admin.port.UpdatePortAction;
import com.java.cruisecompany.controller.action.impl.admin.route.*;
import com.java.cruisecompany.controller.action.impl.admin.user.DeleteUserAction;
import com.java.cruisecompany.controller.action.impl.admin.user.SearchUserAction;
import com.java.cruisecompany.controller.action.impl.admin.user.UpdateUserAction;
import com.java.cruisecompany.controller.action.impl.admin.user.ViewUserAction;
import com.java.cruisecompany.controller.action.impl.common.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        ACTION_MAP.put("add_port", new DeletePortAction());
        ACTION_MAP.put("login", new SignInAction());
        ACTION_MAP.put("sign up", new SignUpAction());
        ACTION_MAP.put("sign_out", new SignOutAction());
        ACTION_MAP.put("search_user", new SearchUserAction());
        ACTION_MAP.put("delete_user", new DeleteUserAction());
        ACTION_MAP.put("view_user", new ViewUserAction());
        ACTION_MAP.put("update_user", new UpdateUserAction());
        ACTION_MAP.put("set_locale", new SetLocaleAction());
        ACTION_MAP.put("manage_port", new ManagePortAction());
        ACTION_MAP.put("create_port", new CreatePortAction());
        ACTION_MAP.put("delete_port", new DeletePortAction());
        ACTION_MAP.put("update_port", new UpdatePortAction());
        ACTION_MAP.put("manage_route", new ManageRouteAction());
        ACTION_MAP.put("view_route", new ViewRouteAction());
        ACTION_MAP.put("delete_waypoint", new DeleteWaypointAction());
        ACTION_MAP.put("add_waypoint", new AddWayPointAction());
        ACTION_MAP.put("delete_route", new DeleteRouteAction());
        ACTION_MAP.put("update_route", new UpdateRouteAction());
        ACTION_MAP.put("create_route", new CreateRouteAction());
    }
    private ActionFactory() {}

    public static Action getAction(HttpServletRequest request) {
        String action = request.getParameter("action");
        System.out.println("received parameter -> " + action);
        return ACTION_MAP.getOrDefault(action, new DefaultAction());
    }
}

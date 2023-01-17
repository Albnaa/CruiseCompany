package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.controller.action.impl.admin.*;
import com.java.cruisecompany.controller.action.impl.common.DefaultAction;
import com.java.cruisecompany.controller.action.impl.common.SignInAction;
import com.java.cruisecompany.controller.action.impl.common.SignOutAction;
import com.java.cruisecompany.controller.action.impl.common.SignUpAction;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        ACTION_MAP.put("add_port", new AddPortAction());
        ACTION_MAP.put("login", new SignInAction());
        ACTION_MAP.put("sign up", new SignUpAction());
        ACTION_MAP.put("sign out", new SignOutAction());
        ACTION_MAP.put("search user by name", new SearchUserAction());
        ACTION_MAP.put("delete_user", new DeleteUserAction());
        ACTION_MAP.put("view_user", new ViewUserAction());
        ACTION_MAP.put("update_user", new UpdateUserAction());
    }
    private ActionFactory() {}

    public static Action getAction(HttpServletRequest request) {
        String action = request.getParameter("action");
        System.out.println("received parameter -> " + action);
        return ACTION_MAP.getOrDefault(action, new DefaultAction());
    }
}

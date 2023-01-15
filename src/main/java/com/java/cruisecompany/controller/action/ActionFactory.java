package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.controller.action.impl.admin.AddPortAction;
import com.java.cruisecompany.controller.action.impl.admin.SearchUserAction;
import com.java.cruisecompany.controller.action.impl.common.DefaultAction;
import com.java.cruisecompany.controller.action.impl.common.SignInAction;
import com.java.cruisecompany.controller.action.impl.common.SignOutAction;
import com.java.cruisecompany.controller.action.impl.common.SignUpAction;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {
    private static final ActionFactory FACTORY = new ActionFactory();
    private static final Map<String, Action> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put("add_port", new AddPortAction());
        COMMAND_MAP.put("login", new SignInAction());
        COMMAND_MAP.put("sign up", new SignUpAction());
        COMMAND_MAP.put("sign out", new SignOutAction());
        COMMAND_MAP.put("search user by name", new SearchUserAction());
    }
    private ActionFactory() {}

    public static ActionFactory getActionFactory() {
        return FACTORY;
    }

    public Action getAction(HttpServletRequest request) {
        String action = request.getParameter("action");
        System.out.println("received parameter -> " + action);
        return COMMAND_MAP.getOrDefault(action, new DefaultAction());
    }
}

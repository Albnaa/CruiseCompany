package com.java.cruisecompany.model.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.*;
import java.util.stream.Collectors;

public class SessionAttributeHandlerUtil {
    private static final Set<String> whiteList = new HashSet<>();

    static {
        whiteList.add("sort");
        whiteList.add("order");
        whiteList.add("roleF");
        whiteList.add("userF");
        whiteList.add("durationF");
        whiteList.add("startDateF");
        whiteList.add("nameF");
    }

    public static void setAttrFromReqToSession(HttpServletRequest request) {
        Map<String, String> sortedAttributes = buildAttrMapFromReq(request);
        HttpSession session = request.getSession();

        System.out.println("Map from sort attributes -> " + sortedAttributes);
        removeAttributesFromSession(session, sortedAttributes);
        setAttributesToSession(session, sortedAttributes);
    }

    private static Map<String, String> buildAttrMapFromReq(HttpServletRequest request) {
        String query = request.getQueryString();

        String[] attributes = query.split("&");
        return Arrays.stream(attributes)
                .filter(attr -> attr.split("=").length == 2)
                .filter(attr -> whiteList.contains(attr.split("=")[0]))
                .collect(Collectors.toMap(attr -> attr.split("=")[0], attr -> attr.split("=")[1]));
    }

    private static void setAttributesToSession(HttpSession session, Map<String, String> attributes) {
        attributes.forEach(session::setAttribute);
    }

    private static void removeAttributesFromSession(HttpSession session, Map<String, String> actualAttributes) {
        whiteList.stream()
                .filter(attr -> !actualAttributes.containsKey(attr))
                .filter(attr -> session.getAttribute(attr) != null)
                .forEach(session::removeAttribute);
    }
}

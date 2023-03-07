package com.java.cruisecompany.model.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The SessionAttributeHandlerUtil class is a utility class that handles attributes between the HttpServletRequest and
 * HttpSession objects. This class provides methods to transfer attributes from the request object to the session object,
 * while also ensuring that only attributes specified in a whitelist are allowed. The class also provides methods to remove
 * any session attributes that are not part of the request, which helps to prevent any issues that could occur from stale
 * session data.
 */
public class SessionAttributeHandlerUtil {
    /**
     * The whitelist of allowed attribute names.
     */
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

    /**
     * Transfers attributes from the HttpServletRequest object to the HttpSession object, while ensuring that only
     * attributes specified in the whitelist are allowed. Any session attributes that are not part of the request are
     * removed.
     *
     * @param request The HttpServletRequest object containing the attributes to transfer to the session
     */
    public static void setAttrFromReqToSession(HttpServletRequest request) {
        Map<String, String> sortedAttributes = buildAttrMapFromReq(request);
        HttpSession session = request.getSession();

        removeAttributesFromSession(session, sortedAttributes);
        setAttributesToSession(session, sortedAttributes);
    }


    /**
     * Builds a map of attributes from the query string of an HttpServletRequest object, while ensuring that only
     * attributes specified in the whitelist are allowed.
     *
     * @param request The HttpServletRequest object containing the query string to build the attribute map from
     * @return A map of attributes from the query string, where the keys are the attribute names and the values are the
     */
    static Map<String, String> buildAttrMapFromReq(HttpServletRequest request) {
        String query = request.getQueryString();

        String[] attributes = query.split("&");
        return Arrays.stream(attributes)
                .filter(attr -> attr.split("=").length == 2)
                .filter(attr -> whiteList.contains(attr.split("=")[0]))
                .collect(Collectors.toMap(attr -> attr.split("=")[0], attr -> attr.split("=")[1]));
    }

    /**
     * Sets attributes to the HttpSession object using the keys and values from the specified attribute map.
     *
     * @param session    The HttpSession object to set the attributes on
     * @param attributes The map of attributes to set on the session, where the keys are the attribute names and the values
     */
    private static void setAttributesToSession(HttpSession session, Map<String, String> attributes) {
        attributes.forEach(session::setAttribute);
    }

    /**
     * Removes any session attributes that are not part of the actual attributes in the specified attribute map, which
     * helps to prevent any issues that could occur from stale session data.
     *
     * @param session          The HttpSession object to remove attributes from
     * @param actualAttributes The map of actual attributes from the HttpServletRequest object, where the keys are the
     */
    private static void removeAttributesFromSession(HttpSession session, Map<String, String> actualAttributes) {
        whiteList.stream()
                .filter(attr -> !actualAttributes.containsKey(attr))
                .filter(attr -> session.getAttribute(attr) != null)
                .forEach(session::removeAttribute);
    }
}

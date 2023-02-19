package com.java.cruisecompany.model.utils;

import jakarta.servlet.http.HttpServletRequest;

public class Pagination {
    public static void calculatePages(HttpServletRequest request, long records) {
        int offset = parseIntFromString(request.getParameter("offset"), 0);
        int rows = parseIntFromString(request.getParameter("rows"), 5);
        int numOfPages = (int) Math.ceil(records * 1.0 / rows);
        int currPage = offset / rows + 1;
        int lowerBound = calculateLowerBound(currPage, numOfPages);
        int upperBound = Math.min(numOfPages, lowerBound + 6);

        request.setAttribute("offset", offset);
        request.setAttribute("rows", rows);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("currPage", currPage);
        request.setAttribute("numOfRows", records);
        request.setAttribute("lowerBound", lowerBound);
        request.setAttribute("upperBound", upperBound);
    }

    static int parseIntFromString(String value, int defaultValue) {
        try {
            if (Long.parseLong(value) > 0) {
                return Integer.parseInt(value);
            } else {
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    static int calculateLowerBound(int currPage, int numOfPages) {
        if (currPage > 6 - (6 / 2)) {
            if (numOfPages > 6) {
                return Math.min(currPage - 6 / 2, numOfPages - 6);
            } else {
                return 1;
            }
        }
        return 1;
    }
}

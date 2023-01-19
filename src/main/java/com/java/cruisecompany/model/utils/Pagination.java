package com.java.cruisecompany.model.utils;

import jakarta.servlet.http.HttpServletRequest;

public class Pagination {
    public static void calculatePages(HttpServletRequest request, long records) {
        long offset = parseIntFromString(request.getParameter("offset"), 0);
        long rows = parseIntFromString(request.getParameter("rows"), 5);
        long numOfPages = (long) Math.ceil(records * 1.0 / rows);
        long currPage = offset / rows + 1;
        long lowerBound = calculateLowerBound(currPage, numOfPages);
        long upperBound = Math.min(numOfPages, lowerBound + 6);

        request.setAttribute("offset", offset);
        request.setAttribute("rows", rows);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("currPage", currPage);
        request.setAttribute("numOfRows", records);
        request.setAttribute("lowerBound", lowerBound);
        request.setAttribute("upperBound", upperBound);
    }

    private static long parseIntFromString(String value, long defaultValue) {
        try {
            if (Long.parseLong(value) > 0) {
                return Long.parseLong(value);
            } else {
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static long calculateLowerBound(long currPage, long numOfPages) {
        if (currPage > (long) 6 - ((long) 6 / 2)) {
            if (numOfPages > 6) {
                return Math.min(currPage - (long) 6 / 2, numOfPages - 6);
            } else {
                return 1;
            }
        }
        return 1;
    }

}

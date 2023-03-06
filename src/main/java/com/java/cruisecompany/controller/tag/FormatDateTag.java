package com.java.cruisecompany.controller.tag;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A custom JSP tag that formats a given LocalDate instance and prints it to the page.
 * <p>
 * The format is determined by the locale of the user session.
 */
public class FormatDateTag extends SimpleTagSupport {
    /**
     * The LocalDate instance to be formatted and printed.
     */
    private LocalDate date;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Formats the date property according to the user's locale and writes it to the page.
     *
     * @throws IOException if an error occurs while writing to the page.
     */
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        String locale = (String) pageContext.getSession().getAttribute("locale");
        DateTimeFormatter formatter;
        if (locale != null && locale.equals("en")) {
            formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        } else {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        }

        out.print(date.format(formatter));
    }
}

package com.java.cruisecompany.controller.tag;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatDateTag extends SimpleTagSupport {
    private LocalDate date;

    public void setDate(LocalDate date) {
        this.date = date;
    }

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
//        JspWriter out = getJspContext().getOut();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        out.print(formatter.format(date));
    }
}

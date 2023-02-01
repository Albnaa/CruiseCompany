package com.java.cruisecompany.controller.tag;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDateTag extends SimpleTagSupport {
    private LocalDate date;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        out.print(formatter.format(date));
    }
}

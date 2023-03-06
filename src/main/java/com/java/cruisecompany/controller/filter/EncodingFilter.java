package com.java.cruisecompany.controller.filter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * The EncodingFilter class is responsible for encoding requests before processing.
 * <p>
 * This filter sets the character encoding for incoming requests to the encoding specified in the configuration file.
 */
public class EncodingFilter implements Filter {
    private String encoding;

    /**
     * Initializes the filter by getting the character encoding from the configuration file.
     *
     * @param config a FilterConfig object containing the filter's configuration parameters
     */
    @Override
    public void init(FilterConfig config) {
        encoding = config.getInitParameter("encoding");
    }

    /**
     * Sets the character encoding for incoming requests to the encoding specified in the configuration file
     * and passes the request and response on to the next filter in the chain.
     *
     * @param request  a ServletRequest object that contains the client's request
     * @param response a ServletResponse object that contains the filter's response
     * @param chain    a FilterChain object that represents the next filter in the chain
     * @throws IOException      if an I/O error occurs while processing the request or response
     * @throws ServletException if the request could not be processed
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}

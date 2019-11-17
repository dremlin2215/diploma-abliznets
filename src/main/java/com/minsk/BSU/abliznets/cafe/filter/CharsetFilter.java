package com.minsk.BSU.abliznets.cafe.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private static final String STANDARD_ENCODING = "UTF-8";

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = STANDARD_ENCODING;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }

        // Set the default response content type and encoding
        servletResponse.setContentType("text/html; charset=" + STANDARD_ENCODING);
        servletResponse.setCharacterEncoding(STANDARD_ENCODING);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

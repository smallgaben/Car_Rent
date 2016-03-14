package controller.internationalization;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private final static Logger logger = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            request.setCharacterEncoding(encoding);
        }

        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Encoding filter started");
        encoding = filterConfig.getInitParameter("encoding");
        logger.info("Get incoding " + encoding);
    }

    public void destroy() {
    }
}

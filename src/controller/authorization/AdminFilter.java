package controller.authorization;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class AdminFilter implements Filter {
    private static final Logger logger=Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}

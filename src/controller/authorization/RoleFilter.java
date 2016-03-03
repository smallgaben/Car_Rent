package controller.authorization;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleFilter implements Filter {
    private static final Logger logger=Logger.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Started Role Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;


        String uri=req.getRequestURI();
        String[] parts=uri.split("/");
        String pass=null;
        for(String i:parts){
            if(i.equals("AdminDir") || i.equals("UserDir") || i.equals("ManagerDir")){
                pass= i;
            }
        }

        if(pass==null){
            filterChain.doFilter(req,resp);
        }
        else{
            if(req.getSession().getAttribute("role").equals("USER") && (pass.equals("AdminDir") || pass.equals("ManagerDir"))){
                logger.error("User can't see Admin's or Manager's pages");
                resp.sendRedirect("/view/UserDir/UserPage.jsp");
            }
            if(req.getSession().getAttribute("role").equals("MANAGER") && (pass.equals("AdminDir") || pass.equals("UserDir"))){
                logger.error("Manager can't see Admin's or User's pages");
                resp.sendRedirect("/view/ManagerDir/ManagerPage.jsp");
            }
            if(req.getSession().getAttribute("role").equals("ADMIN") && (pass.equals("ManagerDir") || pass.equals("UserDir"))){
                logger.error("Admin can't see Manager's or User's pages");
                resp.sendRedirect("/view/AdminDir/AdminPage.jsp");
            }
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}

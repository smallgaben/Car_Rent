package controller.authorization;

import jdk.nashorn.internal.ir.RuntimeNode;
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
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String role = (String) req.getSession().getAttribute("role");

        if(checkPermission(req)){
            filterChain.doFilter(req,resp);
        }else{
            if(role!=null){
                if(role.equals("ADMIN")){
                    logger.error("Admin can't see Manager and User pages");
                    resp.sendRedirect("/carList");
                }
                if(role.equals("MANAGER")){
                    logger.error("Manager can't see Admin and User pages");
                    resp.sendRedirect("/orderList");
                }
                if(role.equals("USER")){
                    logger.error("User can't see Admin and Manager pages");
                    resp.sendRedirect("/carList");
                }
            }
            else {
                logger.error("Unauthorized attempt");
                resp.sendRedirect("/");
            }
        }


    }

    public boolean checkPermission(ServletRequest request){
        HttpServletRequest req=(HttpServletRequest) request;
        String username=(String) req.getSession().getAttribute("username");
        String role = (String) req.getSession().getAttribute("role");
        String uri=req.getRequestURI();

        boolean permission=false;
        logger.info("Request URI: "+uri);

        if(username!=null){
            if(role.equals("ADMIN")){
                for(String s: PathUri.AdminUris){
                    if(uri.contains(s)){
                        permission = true;
                    }
                }
            }
            if(role.equals("MANAGER")){
                for(String s: PathUri.ManagerUris){
                    if(uri.contains(s)){
                        permission=true;
                    }
                }
            }
            if(role.equals("USER")){
                for(String s: PathUri.UserUris){
                    if(uri.contains(s)){
                        permission = true;
                    }
                }
            }

        }else {
            for(String s: PathUri.AnonymousUris){
                if(uri.contains(s)){
                    permission=true;
                }
            }
        }
        return permission;
    }


    @Override
    public void destroy() {

    }
}

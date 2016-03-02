package controller.authentication.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    private static final long serialVersionUID = 2663049600894950587L;
    private static final Logger logger=Logger.getLogger(LogOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Started LogOut Servlet");
        HttpSession session = req.getSession(false);

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + cookie.getValue());
                    break;
                }
            }
        }

        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("/");
    }
}

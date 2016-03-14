package controller.authentication.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    private static final long serialVersionUID = 2663049600894950587L;
    private static final Logger logger = Logger.getLogger(LogOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Started LogOut Servlet");
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("/");
    }
}

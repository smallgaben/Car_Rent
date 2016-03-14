package controller.authentication.servlets;

import model.DAO.UserDAO;
import model.DAOImp.UserDAOImp;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;

public class SignInServlet extends HttpServlet {

    private static final long serialVersionUID = -6283348820572815893L;
    private static final Logger logger = Logger.getLogger(SignInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAOImp = new UserDAOImp();
        User user;
        user = userDAOImp.readByName(req.getParameter("username"));

        if (user != null) {
            logger.info("User exists: " + user.getUsername() + " password: " + user.getPassword());
        }

        if (user != null && req.getParameter("password").equals(user.getPassword()) && !user.getRole().getName().equals("BLOCKED")) {
            logger.info("User successfully got to the system");

            Locale locale = new Locale(req.getParameter("locale"));
            HttpSession session = req.getSession();
            logger.info("Setted locale: " + locale.getLanguage());
            session.setAttribute("locale", locale);
            logger.info("Set a new session: " + session);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole().getName());
            Cookie cookie = new Cookie("username", user.getUsername());
            resp.addCookie(cookie);

            switch (user.getRole().getName()) {
                case "ADMIN":
                    resp.sendRedirect("/adminCarList");
                    break;
                case "USER":
                    resp.sendRedirect("/carList");
                    break;
                case "MANAGER":
                    resp.sendRedirect("/orderList");
                    break;
            }
        } else {
            logger.warn("User blocked or password incorrect");
            resp.sendRedirect("/view/errorPages/BadVal.jsp");
        }
    }
}

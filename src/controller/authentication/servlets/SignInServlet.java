package controller.authentication.servlets;

import model.DAO.UserDAO;
import model.DAOImp.UserDAOImp;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignInServlet extends HttpServlet{

    private static final long serialVersionUID = -6283348820572815893L;
    private static final Logger logger=Logger.getLogger(SignInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAOImp=new UserDAOImp();
        User user=null;
        user=userDAOImp.readByName(req.getParameter("username"));

        if(user!=null){
            logger.info("User exists: "+user.getUsername()+ " password: "+user.getPassword());
        }

        if(user!=null && req.getParameter("password").equals(user.getPassword()) && !user.getRole().getName().equals("BLOCKED")){
            logger.info("User successfully got to the system");

            HttpSession session = req.getSession();
            logger.info("Set a new session: " + session);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole().getName());
            session.setMaxInactiveInterval(30 * 60);

            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(30 * 60);
            resp.addCookie(cookie);

            switch(user.getRole().getName()){
                case "ADMIN":
                    resp.sendRedirect("/carList");
                    break;
                case "USER":
                    resp.sendRedirect("/carList");
                    break;
                case "MANAGER":
                    resp.sendRedirect("/view/ManagerDir/ManagerPage.jsp");
                    break;
            }
        }
        else {
            req.setAttribute("checked",true);
            logger.warn("User blocked or password incorrect");
            req.getRequestDispatcher("/").forward(req,resp);
        }
    }
}

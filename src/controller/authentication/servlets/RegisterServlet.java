package controller.authentication.servlets;

import model.DAO.UserDAO;
import model.DAOImp.RoleDAOImp;
import model.DAOImp.UserDAOImp;
import model.entities.Role;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = -4880969354114420921L;
    private static final Logger logger=Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String firstName=req.getParameter("firstname");
        String lastName=req.getParameter("lastname");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(new RoleDAOImp().read(Role.DEFAULT_ROLE));

        UserDAO userDAO=new UserDAOImp();
        user=userDAO.create(user);

        logger.info("Created customer id and username: "+user.getId() + user.getUsername());

        if(user!=null){
            ServletContext context=getServletContext();
            RequestDispatcher rq=context.getRequestDispatcher("/signin");
            logger.info("Redirecting to SignInServlet.java for entering");
            rq.forward(req,resp);
        }
        else{
            logger.error("Look at 'customerDAO.create' something went wrong");
            req.setAttribute("checked", false);
            req.getRequestDispatcher("/").forward(req,resp);
        }

    }
}

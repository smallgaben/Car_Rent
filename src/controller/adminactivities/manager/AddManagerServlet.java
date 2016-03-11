package controller.adminactivities.manager;

import model.DAO.UserDAO;
import model.DAOImp.RoleDAOImp;
import model.DAOImp.UserDAOImp;
import model.entities.Role;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class AddManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 5783096968278728291L;
    private static final Logger logger=Logger.getLogger(AddManagerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting add a new manager");

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String firstName=req.getParameter("firstname");
        String lastName=req.getParameter("lastname");

        if(username.matches("\\w+") && firstName.matches("^[a-zA-Zа-яА-Я]+$") && lastName.matches("^[a-zA-Zа-яА-Я]+$")){
            User user=new User();
            user.setRole(new RoleDAOImp().read(Role.MANAGER_ROLE));
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            UserDAO userDAO=new UserDAOImp();
            userDAO.create(user);

            resp.sendRedirect("/managerList");
        }
        else{
            badValManager(req,resp);
        }
    }

    private void badValManager(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        logger.error("Bad values for adding manager");
        resp.sendRedirect("/view/errorPages/BadVal.jsp");
    }
}

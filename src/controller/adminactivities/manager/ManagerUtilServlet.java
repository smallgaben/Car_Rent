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

public class ManagerUtilServlet extends HttpServlet {

    private static final long serialVersionUID = 5783096968278728291L;
    private static final Logger logger=Logger.getLogger(ManagerUtilServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting add a new manager");
        User user=new User();
        user.setRole(new RoleDAOImp().read(Role.MANAGER_ROLE));
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));

        UserDAO userDAO=new UserDAOImp();
        userDAO.create(user);

        HashSet<User> managers=new HashSet<>(userDAO.readAll());

        Iterator<User> iterator=managers.iterator();

        while(iterator.hasNext()){
            User s=iterator.next();
            if(!s.getRole().getName().equals("MANAGER")){
                iterator.remove();
            }
        }

        req.setAttribute("managers", managers);

        req.getRequestDispatcher("/view/AdminDir/AdminPage.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO=new UserDAOImp();

        HashSet<User> managers=new HashSet<>(userDAO.readAll());

        Iterator<User> iterator=managers.iterator();

        while(iterator.hasNext()){
            User s=iterator.next();
            if(!s.getRole().getName().equals("MANAGER")){
                iterator.remove();
            }
        }

        req.setAttribute("managers", managers);

        req.getRequestDispatcher("/view/AdminDir/AdminPage.jsp").forward(req,resp);

    }
}

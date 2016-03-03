package controller.adminactivities.user;

import model.DAO.RoleDAO;
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

/**
 * Created by miroslav on 03.03.16.
 */
public class SetBlockedServlet extends HttpServlet {
    private static final long serialVersionUID = -6692450156796813990L;

    private static final Logger logger=Logger.getLogger(SetBlockedServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String test=req.getParameter("blocked");
        int id=Integer.valueOf(req.getParameter("id"));

        UserDAO userDAOImp=new UserDAOImp();
        RoleDAO roleDAOImp=new RoleDAOImp();

        User user=userDAOImp.readById(id);

        if(user.getRole().getName().equals("BLOCKED")){
            logger.info("Making user UNBLOCKED");
            user.setRole(roleDAOImp.read(Role.DEFAULT_ROLE));
            userDAOImp.update(user);
        }
        else {
            logger.info("Making user BLOCKED");
            user.setRole(roleDAOImp.read(Role.BLOCKED_ROLE));
            userDAOImp.update(user);
        }

        resp.sendRedirect("/userList");
    }
}

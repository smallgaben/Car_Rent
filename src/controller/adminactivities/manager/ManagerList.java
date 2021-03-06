package controller.adminactivities.manager;

import model.DAO.UserDAO;
import model.DAOImp.UserDAOImp;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class ManagerList extends HttpServlet {
    private static final long serialVersionUID = 7622332064482365883L;
    private static final Logger logger = Logger.getLogger(ManagerList.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImp();
        HashSet<User> managers = new HashSet<>(userDAO.readAll());
        Iterator<User> iterator = managers.iterator();

        while (iterator.hasNext()) {
            User s = iterator.next();
            if (!s.getRole().getName().equals("MANAGER")) {
                iterator.remove();
            }
        }

        req.setAttribute("managers", managers);

        req.getRequestDispatcher("/view/AdminDir/AdminPage.jsp").forward(req, resp);
    }
}

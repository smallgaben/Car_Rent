package controller.entitylists;

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

public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 7518334532047114221L;
    private static final Logger logger=Logger.getLogger(UserListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Started making users list");

        HashSet<User> users=new HashSet<>(new UserDAOImp().readAll());

        Iterator<User> iterator=users.iterator();

        while(iterator.hasNext()){
            User user=iterator.next();
            if(user.getRole().getName().equals("ADMIN") || user.getRole().getName().equals("MANAGER")){
                iterator.remove();
            }
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("/view/AdminDir/AdminPage.jsp").forward(req,resp);
    }
}

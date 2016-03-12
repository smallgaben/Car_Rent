package controller.authentication.servlets;

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

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = -4880969354114420921L;
    private static final Logger logger=Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO=new UserDAOImp();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String firstName=req.getParameter("firstname");
        String lastName=req.getParameter("lastname");

        if(username.matches("\\w+") && firstName.matches("^[a-zA-Zа-яА-Я]+$") && lastName.matches("^[a-zA-Zа-яА-Я]+$")){
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(new RoleDAOImp().read(Role.DEFAULT_ROLE));

            if(userDAO.readByName(username)!=null){
                req.setAttribute("exist",true);
                req.getRequestDispatcher("/").forward(req,resp);
            }

            user=userDAO.create(user);

            logger.info("Created customer id and username: "+user.getId() + user.getUsername());

            req.getRequestDispatcher("/signin").forward(req,resp);
        }
        else{
            badValRegistr(req, resp);
        }
    }

    private void badValRegistr(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        logger.error("Bad values");
        resp.sendRedirect("/view/errorPages/BadVal.jsp");
    }
}

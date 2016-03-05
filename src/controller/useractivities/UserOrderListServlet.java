package controller.useractivities;

import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.OrderDAOImp;
import model.entities.Check;
import model.entities.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class UserOrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 7131289524120179971L;
    public static final Logger logger=Logger.getLogger(UserOrderListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Making orders list");
        String username= (String) req.getSession().getAttribute("username");
        OrderDAO orderDAO=new OrderDAOImp();
        CheckDAO checkDAO=new CheckDAOImp();
        HashSet<Check> checks=new HashSet<>();

        HashSet<Order> orders=new HashSet<>(orderDAO.readAll());

        for (Order r : orders) {
            if (r.getUser().getUsername().equals(username)) {
                checks.add(checkDAO.readByOrderId(r.getId()));
            }
        }

        req.setAttribute("checks",checks);
        req.getRequestDispatcher("/view/UserDir/UserPage.jsp").forward(req,resp);
    }
}

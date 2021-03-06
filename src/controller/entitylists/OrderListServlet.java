package controller.entitylists;

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

public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 3175340670429888258L;
    private static final Logger logger = Logger.getLogger(OrderListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDAO checkDAO = new CheckDAOImp();
        HashSet<Check> checks = new HashSet<>(checkDAO.readAll());
        OrderDAO orderDAO = new OrderDAOImp();

        for (Order r : orderDAO.readAll()) {
            checks.add(r.getCheck());
        }

        for (Check c : checks) {
            c.setOrders((HashSet<Order>) orderDAO.readByCheck(c.getId()));
        }

        req.setAttribute("checks", checks);

        req.getRequestDispatcher("/view/ManagerDir/ManagerPage.jsp").forward(req, resp);
    }
}

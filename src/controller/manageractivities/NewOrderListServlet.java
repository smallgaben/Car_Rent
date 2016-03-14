package controller.manageractivities;

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

public class NewOrderListServlet extends HttpServlet {
    private static final long serialVersionUID = -4502664141377211471L;
    private static final Logger logger = Logger.getLogger(NewOrderListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDAO checkDAO = new CheckDAOImp();
        HashSet<Check> checks = new HashSet<>(checkDAO.readAll());
        OrderDAO orderDAO = new OrderDAOImp();

        for (Order r : orderDAO.readAll()) {
            checks.add(r.getCheck());
        }

        HashSet<Check> accepting = new HashSet<>();
        HashSet<Check> returned = new HashSet<>();

        for (Check c : checks) {
            c.setOrders((HashSet<Order>) orderDAO.readByCheck(c.getId()));
            if (c.getStatus().getName().equals("not paid")) {
                accepting.add(c);
            }
            if (c.getStatus().getName().equals("success")) {
                for (Order r : c.getOrders()) {
                    if (r.getStatus().getName().equals("returned")) {
                        returned.add(c);
                    }
                }
            }
        }

        logger.info("List of orders need to accept: " + accepting.size());
        logger.info("List of orders returned: " + returned.size());

        req.setAttribute("accepting", accepting);
        req.setAttribute("returned", returned);

        req.getRequestDispatcher("/view/ManagerDir/ManagerPage.jsp").forward(req, resp);
    }
}

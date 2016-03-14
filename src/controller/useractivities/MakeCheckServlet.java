package controller.useractivities;

import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.OrderDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Check;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;

public class MakeCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 47833942179213056L;
    private static final Logger logger = Logger.getLogger(MakeCheckServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAOImp();
        HashSet<Order> orders = new HashSet<>();
        CheckDAO checkDAO = new CheckDAOImp();
        StatusDAO statusDAO = new StatusDAOImp();
        String username = (String) req.getSession().getAttribute("username");
        Check check = new Check();

        for (Order r : orderDAO.readAll()) {
            if (r.getUser().getUsername().equals(username) && r.getStatus().getName().equals("waiting")) {
                orders.add(r);
            }
        }
        check.setOrders(orders);
        check.setPrice(makeTotalPrice(check));
        check.setDescription(Check.ADD_CHECK_DESCR);
        check.setStatus(statusDAO.read(Status.DEFAULT_CHECK_STATUS));
        check.setDate(new Date(System.currentTimeMillis()));
        checkDAO.create(check);

        for (Order r : orders) {
            r.setStatus(statusDAO.read(Status.RENT_ORDER_STATUS));
            r.setCheck(check);
            orderDAO.update(r);
        }

        logger.info("Created check " + check.getId() + " total Price:" + check.getPrice());
        resp.sendRedirect("/userOrders");
    }

    public int makeTotalPrice(Check check) {
        int sum = 0;
        HashSet<Order> orders = check.getOrders();
        for (Order r : orders) {
            sum += makeOrderPrice(r);
        }
        return sum;
    }

    public int makeOrderPrice(Order order) {
        int orderCost = order.getCar().getCost();
        LocalDate startDate = order.getStartDate().toLocalDate();
        LocalDate finishDate = order.getFinishDate().toLocalDate();

        int rez = ((finishDate.getYear() - startDate.getYear()) * 360)
                + ((finishDate.getMonth().getValue() - startDate.getMonth().getValue()) * 30)
                + ((finishDate.getDayOfMonth() - startDate.getDayOfMonth()));

        rez += orderCost * rez;
        if (order.isDriver()) {
            rez += 700;
        }
        return rez;
    }
}

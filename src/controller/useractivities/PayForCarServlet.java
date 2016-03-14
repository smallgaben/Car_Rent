package controller.useractivities;

import model.DAO.CarDAO;
import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DAOImp.CarDAOImp;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.OrderDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Car;
import model.entities.Check;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class PayForCarServlet extends HttpServlet {
    private static final long serialVersionUID = -6327613862025669262L;
    private static final Logger logger = Logger.getLogger(PayForCarServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String state = req.getParameter("repair");
        CheckDAO checkDAO = new CheckDAOImp();
        StatusDAO statusDAO = new StatusDAOImp();
        OrderDAO orderDAO = new OrderDAOImp();
        CarDAO carDAO = new CarDAOImp();

        Car car;

        int id = Integer.valueOf(req.getParameter("id"));
        Check check = checkDAO.read(id);
        check.setOrders((HashSet<Order>) orderDAO.readByCheck(id));

        if (state == null) {
            logger.info("User payed for rent");
            for (Order r : check.getOrders()) {
                car = r.getCar();
                car.setStatus(statusDAO.read(Status.DISABLED_CAR_STATUS));
                carDAO.update(car);

            }

            check.setStatus(statusDAO.read(Status.PAID_CHECK_STATUS));
            check.setDescription(Check.PAID_CHECK_DESCR);

            checkDAO.update(check);
        } else {
            logger.info("User payed for repair");

            for (Order r : check.getOrders()) {
                car = r.getCar();
                car.setStatus(statusDAO.read(Status.DEFAULT_CAR_STATUS));
                carDAO.update(car);
            }

            check.setStatus(statusDAO.read(Status.SUCCESS_CHECK_STATUS));
            check.setDescription(Check.REPAIR_SUCCESS_CHECK_DESCR);
            checkDAO.update(check);
        }

        resp.sendRedirect("/userOrders");
    }
}

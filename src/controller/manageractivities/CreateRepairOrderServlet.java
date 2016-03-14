package controller.manageractivities;

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

public class CreateRepairOrderServlet extends HttpServlet {
    private static final long serialVersionUID = -7128424217612539274L;
    private static final Logger logger = Logger.getLogger(CreateRepairOrderServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDAO checkDAO = new CheckDAOImp();
        OrderDAO orderDAO = new OrderDAOImp();
        StatusDAO statusDAO = new StatusDAOImp();

        int id = Integer.valueOf(req.getParameter("id"));

        Order order = orderDAO.readById(id);
        order.setStatus(statusDAO.read(Status.REPAIR_ORDER_STATUS));
        orderDAO.update(order);

        logger.info("Making check for repair");
        Check check = new Check();
        check.setStatus(statusDAO.read(Status.ACCEPTED_CHECK_STATUS));
        check.setDate(new Date(System.currentTimeMillis()));
        check.setDescription(req.getParameter("description"));
        check.setPrice(Integer.valueOf(req.getParameter("cost")));
        checkDAO.create(check);

        order.setPassport(order.getPassport());
        order.setStartDate(Date.valueOf(req.getParameter("startDate")));
        order.setFinishDate(Date.valueOf(req.getParameter("finishDate")));
        order.setDriver(false);
        orderDAO.create(order);

        order.setCheck(check);

        orderDAO.update(order);

        resp.sendRedirect("/orderList");
    }
}

package controller.useractivities;

import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DAO.UserDAO;
import model.DAOImp.CarDAOImp;
import model.DAOImp.OrderDAOImp;
import model.DAOImp.StatusDAOImp;
import model.DAOImp.UserDAOImp;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RentCarServlet extends HttpServlet {
    private static final long serialVersionUID = -1180725372945608661L;
    private static final Logger logger = Logger.getLogger(RentCarServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAOImp();
        CarDAO carDAO = new CarDAOImp();
        UserDAO userDAO = new UserDAOImp();
        StatusDAO statusDAO = new StatusDAOImp();

        int id = Integer.valueOf(req.getParameter("id"));
        String username = (String) req.getSession().getAttribute("username");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date finishDate = Date.valueOf(req.getParameter("finishDate"));
        Date currentDate = new Date(System.currentTimeMillis());
        String reqDriver = req.getParameter("driver");
        String passport = req.getParameter("passport");

        boolean driver = false;
        if (reqDriver != null) {
            driver = reqDriver.equals("on");
        }

        if (startDate.before(currentDate) || finishDate.before(startDate)) {
            badValRedirect(req, resp);
        } else {
            Order order = new Order();
            order.setCar(carDAO.readById(id));
            order.setUser(userDAO.readByName(username));
            order.setStartDate(startDate);
            order.setFinishDate(finishDate);
            order.setStatus(statusDAO.read(Status.WAIT_ORDER_STATUS));
            order.setPassport(passport);
            order.setDriver(driver);

            orderDAO.create(order);
            resp.sendRedirect("/userOrders");
        }
    }

    public void badValRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.error("Bad order values");
        resp.sendRedirect("/view/errorPages/BadVal.jsp");
    }
}

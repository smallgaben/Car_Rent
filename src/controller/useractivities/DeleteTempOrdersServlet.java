package controller.useractivities;

import model.DAO.OrderDAO;
import model.DAOImp.OrderDAOImp;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTempOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = -2625821865652955510L;
    private static final Logger logger = Logger.getLogger(DeleteTempOrdersServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAOImp();
        String username = (String) req.getSession().getAttribute("username");

        int counter = 0;
        for (Order r : orderDAO.readAll()) {
            if (r.getUser().getUsername().equals(username) && r.getStatus().getId() == Status.WAIT_ORDER_STATUS) {
                orderDAO.delete(r.getId());
                counter++;
            }
        }

        logger.info("Deleted +" + counter + " temp orders");
        resp.sendRedirect("/userOrders");
    }
}

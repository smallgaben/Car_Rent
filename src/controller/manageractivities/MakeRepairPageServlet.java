package controller.manageractivities;

import model.DAO.OrderDAO;
import model.DAOImp.OrderDAOImp;
import model.entities.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeRepairPageServlet extends HttpServlet {
    private static final long serialVersionUID = 5863996801075873145L;
    private static final Logger logger = Logger.getLogger(MakeRepairPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Making a new page for repair order");
        OrderDAO orderDAO = new OrderDAOImp();
        int id = Integer.valueOf(req.getParameter("id"));
        Order order = orderDAO.readById(id);

        req.setAttribute("order", order);
        req.getRequestDispatcher("/view/ManagerDir/RepairPage.jsp").forward(req, resp);

    }
}

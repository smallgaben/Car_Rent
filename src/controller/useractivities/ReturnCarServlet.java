package controller.useractivities;

import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.OrderDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Check;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnCarServlet extends HttpServlet {
    private static final long serialVersionUID = -2175686993845853486L;
    private static final Logger logger=Logger.getLogger(ReturnCarServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Returning Car");

        int id=Integer.valueOf(req.getParameter("id"));
        CheckDAO checkDAO=new CheckDAOImp();
        OrderDAO orderDAO=new OrderDAOImp();
        StatusDAO statusDAO=new StatusDAOImp();

        Check check=checkDAO.readByOrderId(id);
        check.setStatus(statusDAO.read(Status.SUCCESS_CHECK_STATUS));
        check.getOrder().setStatus(statusDAO.read(Status.RETURN_ORDER_STATUS));
        check.setDescription(Check.SUCCESS_APPROV_CHECK_DESCR);

        checkDAO.update(check);
        orderDAO.update(check.getOrder());

        resp.sendRedirect("/userOrders");
    }
}

package controller.manageractivities;

import model.DAO.CarDAO;
import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DAOImp.CarDAOImp;
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


public class FinishOrderServlet extends HttpServlet {
    private static final long serialVersionUID = -2888943039571734066L;
    private static final Logger logger=Logger.getLogger(FinishOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Finishing order");

        int id=Integer.valueOf(req.getParameter("id"));
        CheckDAO checkDAO=new CheckDAOImp();
        StatusDAO statusDAO=new StatusDAOImp();
        OrderDAO orderDAO=new OrderDAOImp();
        CarDAO carDAO=new CarDAOImp();

        Check check=checkDAO.readByOrderId(id);
        check.getOrder().getCar().setStatus(statusDAO.read(Status.DEFAULT_CAR_STATUS));
        check.setStatus(statusDAO.read(Status.SUCCESS_CHECK_STATUS));
        check.getOrder().setStatus(statusDAO.read(Status.RENT_ORDER_STATUS));
        check.setDescription(Check.SUCCESS_FINISH_CHECK_DESCR);

        orderDAO.update(check.getOrder());
        carDAO.update(check.getOrder().getCar());
        checkDAO.update(check);

        resp.sendRedirect("/orderList");
    }
}

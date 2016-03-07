package controller.useractivities;

import model.DAO.CarDAO;
import model.DAO.CheckDAO;
import model.DAO.StatusDAO;
import model.DAOImp.CarDAOImp;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Car;
import model.entities.Check;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayForCarServlet extends HttpServlet {
    private static final long serialVersionUID = -6327613862025669262L;
    private static final Logger logger=Logger.getLogger(PayForCarServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String state=req.getParameter("repair");
        CheckDAO checkDAO=new CheckDAOImp();
        StatusDAO statusDAO=new StatusDAOImp();
        CarDAO carDAO=new CarDAOImp();

        int id=Integer.valueOf(req.getParameter("id"));
        Check check=checkDAO.readByOrderId(id);
        Car car=carDAO.readById(check.getOrder().getCar().getId());

        if(state==null){
            logger.info("User payed for rent");
            car.setStatus(statusDAO.read(Status.DISABLED_CAR_STATUS));
            check.getOrder().getCar().setStatus(statusDAO.read(Status.DISABLED_CAR_STATUS));
            check.setStatus(statusDAO.read(Status.PAID_CHECK_STATUS));
            check.setDescription(Check.PAID_CHECK_DESCR);

            carDAO.update(car);
            checkDAO.update(check);

        }
        else{
            logger.info("User payed for repair");
            car.setStatus(statusDAO.read(Status.DEFAULT_CAR_STATUS));
            carDAO.update(car);

            check.setStatus(statusDAO.read(Status.SUCCESS_CHECK_STATUS));
            check.setDescription(Check.REPAIR_SUCCESS_CHECK_DESCR);
            checkDAO.update(check);
        }

        resp.sendRedirect("/userOrders");
    }
}

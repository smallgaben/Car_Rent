package controller.useractivities;

import model.DAO.CarDAO;
import model.DAOImp.CarDAOImp;
import model.entities.Car;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RentPageServlet extends HttpServlet {
    private static final long serialVersionUID = -5676998019204658441L;
    private static final Logger logger = Logger.getLogger(RentCarServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Making order page");

        CarDAO carDAO = new CarDAOImp();
        int id = Integer.valueOf(req.getParameter("id"));
        Car car = carDAO.readById(id);

        req.setAttribute("id", id);
        req.setAttribute("mark", car.getMark());
        req.setAttribute("name", car.getName());
        req.setAttribute("cost", car.getCost());
        req.setAttribute("carClass", car.getCarClass().getName());
        req.getRequestDispatcher("/view/UserDir/OrderPage.jsp").forward(req, resp);
    }
}

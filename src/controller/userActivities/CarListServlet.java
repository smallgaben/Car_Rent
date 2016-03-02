package controller.userActivities;

import model.DAOImp.CarDAOImp;
import model.entities.Car;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class CarListServlet extends HttpServlet {
    private static final long serialVersionUID = -5957303985247942933L;
    private static final Logger logger=Logger.getLogger(CarListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDAOImp carDAOImp=new CarDAOImp();
        logger.info("Making list of Cars");
        HashSet<Car> cars=new HashSet<>(carDAOImp.readAll());
        req.setAttribute("cars",cars);
        req.getRequestDispatcher("/view/UserDir/UserPage.jsp").forward(req,resp);
    }
}

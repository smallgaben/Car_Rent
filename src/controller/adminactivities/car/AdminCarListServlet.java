package controller.adminactivities.car;

import model.DAO.CarDAO;
import model.DAOImp.CarDAOImp;
import model.entities.Car;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class AdminCarListServlet extends HttpServlet {
    private static final long serialVersionUID = 3637637807938956956L;
    private static final Logger logger = Logger.getLogger(AdminCarListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDAO carDAOImp = new CarDAOImp();
        logger.info("Making list of Cars");
        HashSet<Car> cars = new HashSet<>(carDAOImp.readAll());
        req.setAttribute("cars", cars);

        req.getRequestDispatcher("/view/AdminDir/AdminPage.jsp").forward(req, resp);
    }
}

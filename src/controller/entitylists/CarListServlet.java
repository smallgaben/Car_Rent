package controller.entitylists;

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
import java.util.Set;

public class CarListServlet extends HttpServlet {
    private static final long serialVersionUID = -5957303985247942933L;
    private static final Logger logger = Logger.getLogger(CarListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarDAO carDAOImp = new CarDAOImp();
        logger.info("Making list of Cars");
        HashSet<Car> cars = new HashSet<>(carDAOImp.readAll());
        req.setAttribute("cars", cars);

            setReqMarks(cars, req);
            setReqClasses(cars, req);
            req.getRequestDispatcher("/view/UserDir/UserPage.jsp").forward(req, resp);

    }

    /**
     * Method for making car names list
     *
     * @param cars list of cars
     * @param req  request for attribute
     */
    public void setReqMarks(Set<Car> cars, HttpServletRequest req) {
        HashSet<String> marks = new HashSet<>();
        for (Car s : cars) {
            marks.add(s.getMark());
        }
        req.setAttribute("marks", marks);
    }

    /**
     * Method for making car class names list
     *
     * @param cars list of cars
     * @param req  request for attribute
     */
    public void setReqClasses(Set<Car> cars, HttpServletRequest req) {
        HashSet<String> classes = new HashSet<>();
        for (Car s : cars) {
            classes.add(s.getCarClass().getName());
        }
        req.setAttribute("classes", classes);
    }
}

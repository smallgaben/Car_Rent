package controller.useractivities;

import controller.entitylists.CarListServlet;
import model.DAO.CarDAO;
import model.DAOImp.CarDAOImp;
import model.entities.Car;
import org.apache.log4j.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class UserSortServlet extends HttpServlet {
    private static final long serialVersionUID = -5065822043071965544L;
    private static final Logger logger = Logger.getLogger(UserSortServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting to Sort Cars");
        CarDAO carDAO = new CarDAOImp();
        HashSet<Car> cars = new HashSet<>(carDAO.readAll());
        Iterator<Car> iterator = cars.iterator();

        if (req.getParameter("mark") != null) {
            while (iterator.hasNext()) {
                if (!iterator.next().getMark().equals(req.getParameter("mark"))) {
                    iterator.remove();
                }
            }
            req.setAttribute("cars", cars);
        }
        if (req.getParameter("carClass") != null) {
            while (iterator.hasNext()) {
                if (!iterator.next().getCarClass().getName().equals(req.getParameter("carClass"))) {
                    iterator.remove();
                }
            }
            req.setAttribute("cars", cars);
        }
        if (Boolean.valueOf(req.getParameter("cost"))) {
            List carByCost = new ArrayList<>(cars);
            Collections.sort(carByCost, new Comparator<Car>() {

                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getCost() - o2.getCost();
                }
            });
            req.setAttribute("cars", carByCost);
        }
        if (Boolean.valueOf(req.getParameter("name"))) {
            List carByName = new ArrayList<>(cars);
            Collections.sort(carByName, new Comparator<Car>() {

                @Override
                public int compare(Car o1, Car o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            req.setAttribute("cars", carByName);
        }

        CarListServlet carList = new CarListServlet();
        cars = new HashSet<>(carDAO.readAll());
        carList.setReqClasses(cars, req);
        carList.setReqMarks(cars, req);
        req.getRequestDispatcher("/view/UserDir/UserPage.jsp").forward(req, resp);
    }
}

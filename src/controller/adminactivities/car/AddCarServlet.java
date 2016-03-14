package controller.adminactivities.car;

import model.DAOImp.CarDAOImp;
import model.DAOImp.ClassDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Car;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCarServlet extends HttpServlet {
    private static final long serialVersionUID = 3503627204818090220L;

    private static final Logger logger = Logger.getLogger(AddCarServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting making car");

        if (req.getParameter("cost").matches("^\\d+") && req.getParameter("class").matches("\\d")) {
            Car car = new Car();
            car.setMark(req.getParameter("mark"));
            car.setName(req.getParameter("name"));
            car.setCost(Integer.valueOf(req.getParameter("cost")));
            car.setCarClass(new ClassDAOImp().read(Integer.valueOf(req.getParameter("class"))));
            car.setStatus(new StatusDAOImp().read(Status.DEFAULT_CAR_STATUS));

            new CarDAOImp().create(car);

            resp.sendRedirect("/carList");
        } else {
            badValAdd(req, resp);
        }
    }

    private void badValAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.error("Bad values by adding car");
        resp.sendRedirect("/view/errorPages/BadVal.jsp");
    }
}

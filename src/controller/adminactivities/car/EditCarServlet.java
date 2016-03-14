package controller.adminactivities.car;

import model.DAOImp.CarDAOImp;
import model.DAOImp.ClassDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Car;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCarServlet extends HttpServlet {
    private static final long serialVersionUID = -5032687388314712702L;
    private static final Logger logger = Logger.getLogger(EditCarServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting editing Car");
        Car car = new Car();

        if (req.getParameter("cost").matches("^\\d+$")
                && req.getParameter("class").matches("^\\d$")
                && req.getParameter("status").matches("^\\d$")) {

            int id = Integer.valueOf(req.getParameter("id"));
            String mark = req.getParameter("mark");
            String name = req.getParameter("name");
            int cost = Integer.valueOf(req.getParameter("cost"));
            int carClass = Integer.valueOf(req.getParameter("class"));
            int status = Integer.valueOf(req.getParameter("status"));

            car.setId(id);
            car.setMark(mark);
            car.setName(name);
            car.setCost(cost);
            car.setCarClass(new ClassDAOImp().read(carClass));
            car.setStatus(new StatusDAOImp().read(status));
            logger.info("Editing car: " + car);
            new CarDAOImp().update(car);
            resp.sendRedirect("/carList");
        } else {
            badValEdit(req, resp);
        }
    }

    private void badValEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.error("Bad values for editing car!");
        resp.sendRedirect("/view/errorPages/BadVal.jsp");
    }
}

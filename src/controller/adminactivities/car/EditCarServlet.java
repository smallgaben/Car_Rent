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
    private static final Logger logger=Logger.getLogger(EditCarServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting editing Car");
        Car car=new Car();
        car.setId(Integer.valueOf(req.getParameter("id")));
        car.setMark(req.getParameter("mark"));
        car.setName(req.getParameter("name"));
        car.setCost(Integer.valueOf(req.getParameter("cost")));
        car.setCarClass(new ClassDAOImp().read(Integer.valueOf(req.getParameter("class"))));
        car.setStatus(new StatusDAOImp().read(Integer.valueOf(req.getParameter("status"))));

        logger.info("Editing car: "+car);
        new CarDAOImp().update(car);

        resp.sendRedirect("/carList");
    }
}

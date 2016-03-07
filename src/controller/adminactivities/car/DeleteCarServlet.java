package controller.adminactivities.car;

import model.DAO.CarDAO;
import model.DAOImp.CarDAOImp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCarServlet extends HttpServlet {
    private static final long serialVersionUID = 6935239549787613665L;
    private static final Logger logger=Logger.getLogger(DeleteCarServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting Delete Car Servlet");
        int id=Integer.valueOf(req.getParameter("id"));
        logger.info("Need to delete car with id: "+ id);

        CarDAO carDAO=new CarDAOImp();
        carDAO.delete(id);

        resp.sendRedirect("/carList");
    }
}

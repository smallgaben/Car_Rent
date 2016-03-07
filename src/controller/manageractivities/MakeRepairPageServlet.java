package controller.manageractivities;

import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.OrderDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Check;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeRepairPageServlet extends HttpServlet {
    private static final long serialVersionUID = 5863996801075873145L;
    private static final Logger logger=Logger.getLogger(MakeRepairPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Making a new page for repair order");
        CheckDAO checkDAO=new CheckDAOImp();
        int id=Integer.valueOf(req.getParameter("id"));
        Check check=checkDAO.readByOrderId(id);

            req.setAttribute("check",check);
            req.getRequestDispatcher("/view/ManagerDir/RepairPage.jsp").forward(req,resp);

    }
}

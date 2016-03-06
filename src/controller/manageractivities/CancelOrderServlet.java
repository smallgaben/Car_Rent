package controller.manageractivities;

import model.DAO.CheckDAO;
import model.DAOImp.CheckDAOImp;
import model.DAOImp.StatusDAOImp;
import model.entities.Check;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelOrderServlet extends HttpServlet {
    private static final long serialVersionUID = -9098929579581688696L;
    private static final Logger logger=Logger.getLogger(CancelOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Cancelling order");
        int id=Integer.valueOf(req.getParameter("id"));
        String description=req.getParameter("description");

        if(description==null || description.equals(Check.ADD_CHECK_DESCR) || description.isEmpty()){
            description=Check.CANCEL_CHECK_DESCR;
        }

        CheckDAO checkDAO=new CheckDAOImp();
        Check check=checkDAO.readByOrderId(id);
        check.setStatus(new StatusDAOImp().read(Status.CANCELED_CHECK_STATUS));
        check.setDescription(description);

        checkDAO.update(check);

        resp.sendRedirect("/orderList");
    }
}

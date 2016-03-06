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

public class ConfirmOrderServlet extends HttpServlet {
    private static final long serialVersionUID = -8752051292881288815L;
    private static final Logger logger=Logger.getLogger(ConfirmOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Confirming order");
        int id=Integer.valueOf(req.getParameter("id"));
        String description=req.getParameter("description");
        CheckDAO checkDAO=new CheckDAOImp();
        Check check=checkDAO.readByOrderId(id);
        check.setStatus(new StatusDAOImp().read(Status.ACCEPTED_CHECK_STATUS));

        if(description==null || description.equals(Check.ADD_CHECK_DESCR) || description.isEmpty()){
            description = Check.ACCEPTED_CHECK_DESCR;
        }

        check.setDescription(description);

        checkDAO.update(check);

        resp.sendRedirect("/orderList");
    }
}

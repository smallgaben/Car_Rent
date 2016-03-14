package controller.manageractivities;

import model.DAO.CheckDAO;
import model.DAO.StatusDAO;
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
    private static final Logger logger = Logger.getLogger(ConfirmOrderServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Confirming check");
        int id = Integer.valueOf(req.getParameter("id"));
        CheckDAO checkDAO = new CheckDAOImp();
        StatusDAO statusDAO = new StatusDAOImp();
        Check check = checkDAO.read(id);
        check.setStatus(statusDAO.read(Status.ACCEPTED_CHECK_STATUS));

        check.setDescription(Check.ACCEPTED_CHECK_DESCR);

        checkDAO.update(check);

        resp.sendRedirect("/orderList");
    }
}

package controller.entitylists;

import model.DAO.CheckDAO;
import model.DAOImp.CheckDAOImp;
import model.entities.Check;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 3175340670429888258L;
    private static final Logger logger=Logger.getLogger(OrderListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDAO checkDAO=new CheckDAOImp();
        HashSet<Check> checks=new HashSet<>(checkDAO.readAll());
        req.setAttribute("checks",checks);

        if(req.getParameter("newOrders")!=null){
            req.setAttribute("newOrders",true);
        }
        req.getRequestDispatcher("/view/ManagerDir/ManagerPage.jsp").forward(req,resp);
    }
}

package controller.useractivities;

import model.DAO.*;
import model.DAOImp.*;
import model.entities.Car;
import model.entities.Check;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RentCarServlet extends HttpServlet {
    private static final long serialVersionUID = -1180725372945608661L;
    private static final Logger logger = Logger.getLogger(RentCarServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDAO carDAO = new CarDAOImp();
        int id=Integer.valueOf(req.getParameter("id"));
        Car car = carDAO.readById(id);
        req.setAttribute("id",id);

        if(req.getParameter("passport")!=null){
            logger.info("Making a new order");
            makeAnOrder(req, resp);
        }
        else{
            logger.info("Making order page");
            req.setAttribute("mark", car.getMark());
            req.setAttribute("name", car.getName());
            req.setAttribute("cost", car.getCost());
            req.setAttribute("carClass", car.getCarClass().getName());
            req.getRequestDispatcher("/view/UserDir/OrderPage.jsp").forward(req, resp);
        }

    }

    public Order makeAnOrder(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException{
        OrderDAO orderDAO=new OrderDAOImp();
        CarDAO carDAO=new CarDAOImp();
        UserDAO userDAO=new UserDAOImp();
        StatusDAO statusDAO=new StatusDAOImp();

        int id=Integer.valueOf(req.getParameter("id"));
        String username=(String) req.getSession().getAttribute("username");
        Date startDate=Date.valueOf(req.getParameter("startDate"));
        Date finishDate=Date.valueOf(req.getParameter("finishDate"));
        Date currentDate=new Date(System.currentTimeMillis());
        String reqDriver= req.getParameter("driver");
        String passport=req.getParameter("passport");

        boolean driver=false;
        if(reqDriver!=null){
            driver=reqDriver.equals("on");
        }

        if(startDate.before(currentDate) || finishDate.before(startDate)){
            badValRedirect(req, resp);
        }
        else{
            Order order=new Order();
            order.setCar(carDAO.readById(id));
            order.setUser(userDAO.readByName(username));
            order.setStartDate(startDate);
            order.setFinishDate(finishDate);
            order.setStatus(statusDAO.read(Status.RENT_ORDER_STATUS));
            order.setPassport(passport);
            order.setDriver(driver);
            resp.sendRedirect("/userOrders");
            return orderDAO.create(order);
        }
        return null;
    }

    public void badValRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        logger.error("Bad order values");
        req.setAttribute("checked",false);
        resp.sendRedirect("/view/errorPages/BadValOrder.jsp");
    }

}

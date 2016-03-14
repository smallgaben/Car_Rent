package controller.useractivities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnServlet extends HttpServlet {
    private static final long serialVersionUID = 4827804463106286562L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");

        if (role != null) {
            switch (role) {
                case "User":
                    resp.sendRedirect("/carList");
                    break;
                case "ADMIN":
                    resp.sendRedirect("/adminCarList");
                    break;
                case "Manager":
                    resp.sendRedirect("/orderList");
                    break;
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}

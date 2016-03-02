import model.DBUtil.DSHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class test extends HttpServlet {
    private static final long serialVersionUID = -5151771793806996624L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DSHolder.getInstance().getConnection();
    }
}

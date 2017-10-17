package servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.ADMIN_CABINET;
import static utils.Settings.DEPLOY_PATH;

public class CreateClassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createClass.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO some logic to add class
        resp.sendRedirect(DEPLOY_PATH + ADMIN_CABINET);
    }
}

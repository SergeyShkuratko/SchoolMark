package servlets.admin;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.ADMIN_CABINET;
import static utils.Settings.DEPLOY_PATH;

public class CreateClassServlet extends HttpServlet {

    Logger logger = Logger.getLogger(CreateClassServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String school = req.getParameter("school");
        if (city != null && school != null) {
            try {
                int cityId = Integer.valueOf(city);
                int schoolId = Integer.valueOf(school);
                req.setAttribute("city", cityId);
                req.setAttribute("school", schoolId);
                req.getRequestDispatcher("/createClass.jsp").forward(req, resp);
            } catch (ClassCastException e) {
                logger.debug(e);
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            logger.info("Parameters not found");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO some logic to add class
        resp.sendRedirect(DEPLOY_PATH + ADMIN_CABINET);
    }
}

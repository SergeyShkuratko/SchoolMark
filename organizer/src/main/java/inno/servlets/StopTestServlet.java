package inno.servlets;


import inno.dao.TestDAO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test-stop")
public class StopTestServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(StopTestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("cp1251");
        int test_id = req.getParameter("test_id") != null ?
                Integer.parseInt(req.getParameter("test_id")) : 0;
        if (test_id > 0) {
            try {
                if (TestDAO.doneTest(test_id)) {
                    req.getRequestDispatcher("test_stop.jsp").forward(req, resp);
                }
            } catch (OrganizerDAOexception organizerDAOexception) {
                logger.error(organizerDAOexception);
                organizerDAOexception.printStackTrace();
            }
        }
    }


}


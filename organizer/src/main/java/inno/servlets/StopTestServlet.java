package inno.servlets;


import inno.dao.TestDAO;
import inno.exceptions.OrganizerDAOexception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StopTestServlet extends HttpServlet {
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
                organizerDAOexception.printStackTrace();
            }
        }
    }


}


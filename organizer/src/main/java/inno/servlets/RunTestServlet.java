package inno.servlets;

import inno.dao.OrganizerDAO;
import inno.dao.TestDAO;
import inno.dao.WorkDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RunTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("cp1251");
        int test_id = req.getParameter("test_id") != null ?
                Integer.parseInt(req.getParameter("test_id")) : 0;
        if (test_id > 0) {
            try {
                if (!OrganizerDAO.isWorksExists(test_id))
                    OrganizerDAO.createWorksForTest(test_id); //создаем работы, если еще нет

                //получаем тест из БД
                TestDTO test = OrganizerDAO.getTestById(test_id);

                //добавляем работы
                req.setAttribute("works", OrganizerDAO.getAllWorksByTestId(test.getId(), "new"));

                //добавляем тест
                req.setAttribute("test", test);
            } catch (OrganizerDAOexception e) {
                throw new ServletException(e);
            }
        }
        req.getRequestDispatcher("test_run.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getAttribute("work_id") != null) {
            if (req.getAttribute("presence") != null) {
                try {
                   resp.getWriter().print(
                           WorkDAO.updatePresenceStatusByID(
                            Integer.parseInt((String) req.getAttribute("work_id")),
                            Boolean.parseBoolean((String) req.getAttribute("presence")))
                    );
                } catch (OrganizerDAOexception organizerDAOexception) {
                    organizerDAOexception.printStackTrace();
                }
            }
        }
    }
}

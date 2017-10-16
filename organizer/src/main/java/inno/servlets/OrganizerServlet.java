package inno.servlets;

import inno.dao.TestDAOImpl;
import inno.dto.TestDTO;
import inno.exceptions.TestDTOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrganizerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("cp1251");
        int test_id = req.getParameter("test_id") != null ?
                Integer.parseInt(req.getParameter("test_id")) : 0;

        if (test_id > 0) {
            try {
                req.setAttribute("testDTO", TestDAOImpl.getById(test_id));
            } catch (TestDTOException e) {
                throw new ServletException(e);
            }

        }

        req.getRequestDispatcher("organizer.jsp").forward(req, resp);
    }
}

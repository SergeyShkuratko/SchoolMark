package servlets.admin.lists;

import classes.School;
import dao.CityDAOImpl;
import dao.SchoolDAOImpl;
import exceptions.CityDAOException;
import exceptions.SchoolDAOException;
import interfaces.dao.CityDAO;
import interfaces.dao.SchoolDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.Settings.ERROR_ATTR;
import static utils.Settings.ERROR_JSP;

public class SchoolListServlet extends HttpServlet {

    private SchoolDAO schoolDAO = new SchoolDAOImpl();
    private CityDAO cityDAO = new CityDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int cityId = Integer.parseInt(req.getParameter("city"));
            resp.setCharacterEncoding("UTF-8");
            if (cityId > 0) {
                PrintWriter pw = resp.getWriter();
                List<School> schools = schoolDAO.getAllSchoolsInCity(cityDAO.getById(cityId));
                schools.stream().forEach((s) -> pw.println("<option value='" + s.getId() + "'>" + s.getName() + "</option>"));
            }
        } catch (SchoolDAOException | CityDAOException e) {
            req.setAttribute(ERROR_ATTR, DB_ERROR);
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }
}

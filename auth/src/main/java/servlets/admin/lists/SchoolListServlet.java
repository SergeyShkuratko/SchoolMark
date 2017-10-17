package servlets.admin.lists;

import classes.School;
import dao.SchoolDAOImpl;
import interfaces.dao.SchoolDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SchoolListServlet extends HttpServlet {

    private SchoolDAO schoolDAO = new SchoolDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String region = req.getParameter("region");
        resp.setCharacterEncoding("UTF-8");
        if (city != null && region != null) {
            PrintWriter pw = resp.getWriter();
            List<School> schools = schoolDAO.getAllSchoolsInCity(city, region);
            schools.stream().forEach((s) -> pw.println("<option value='" + s.getId() + "'>" + s.getName() + "</option>"));
        }
    }
}

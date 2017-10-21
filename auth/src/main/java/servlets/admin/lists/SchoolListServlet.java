package servlets.admin.lists;

import classes.School;
import classes.dto.SchoolDTO;
import dao.CityDAOImpl;
import dao.SchoolDAOImpl;
import exceptions.CityDAOException;
import exceptions.SchoolDAOException;
import interfaces.dao.CityDAO;
import interfaces.dao.SchoolDAO;
import interfaces.dao.SchoolsDAO;
import services.AdminDataRequestsProcessingService;
import services.impl.AdminDataRequestsProcessingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SchoolListServlet extends HttpServlet {

    AdminDataRequestsProcessingService processingService = new AdminDataRequestsProcessingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int cityId = Integer.parseInt(req.getParameter("city"));
            resp.setCharacterEncoding("UTF-8");
            if (cityId > 0) {
                PrintWriter pw = resp.getWriter();
                List<SchoolDTO> schools = processingService.getSchoolsByLocation(cityId);
                schools.stream().forEach((s) -> pw.println("<option value='" + s.id + "'>" + s.name + "</option>"));
            }
        } catch (SchoolDAOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

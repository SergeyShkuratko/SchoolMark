package servlets.admin.lists;

import classes.School;
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
        String city = req.getParameter("city");
        String region = req.getParameter("region");
        resp.setCharacterEncoding("UTF-8");
        if (city != null && region != null) {
            PrintWriter pw = resp.getWriter();
            List<School> schools = processingService.getSchoolsByLocation(city, region);
            schools.stream().forEach((s) -> pw.println("<option value='" + s.getId() + "'>" + s.getName() + "</option>"));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

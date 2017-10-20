package servlets.admin.lists;

import classes.Teacher;
import services.AdminDataRequestsProcessingService;
import services.impl.AdminDataRequestsProcessingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TeacherListServlet extends HttpServlet {

    private AdminDataRequestsProcessingService processingService = new AdminDataRequestsProcessingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("school");
        if (param != null) {
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pw = resp.getWriter();
            int id = Integer.valueOf(req.getParameter("school"));
            List<Teacher> classes = processingService.getTeachersBySchoolId(id);
            classes.stream().forEach((t) -> pw.println("<li>" + t.getFullName() + "</li>"));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}

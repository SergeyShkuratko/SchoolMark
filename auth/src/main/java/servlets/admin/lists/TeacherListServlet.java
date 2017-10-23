package servlets.admin.lists;

import exceptions.SchoolTeacherDAOException;
import org.apache.log4j.Logger;
import services.TeacherService;
import services.impl.TeacherServiceImpl;
import utils.ForwardRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.ForwardRequestHelper.getErrorDispatcher;

public class TeacherListServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(TeacherListServlet.class);

    private TeacherService processingService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("school");
        if (param != null) {
            try {
                resp.setCharacterEncoding("UTF-8");
                PrintWriter pw = resp.getWriter();
                int id = Integer.valueOf(req.getParameter("school"));
                List<String> teacherNames = null;
                teacherNames = processingService.getTeacherNamesBySchoolId(id);
                teacherNames.stream().forEach((t) -> pw.println("<li>" + t + "</li>"));
            } catch (NumberFormatException | SchoolTeacherDAOException e) {
                logger.info(e);
                getErrorDispatcher(req, DB_ERROR).forward(req, resp);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

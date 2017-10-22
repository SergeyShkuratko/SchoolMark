package servlets.admin.lists;

import classes.SchoolClass;
import exceptions.SchoolDAOException;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static utils.Settings.DEPLOY_PATH;
import static utils.Settings.REG_LINK_PAGE;

public class ClassesListServlet extends HttpServlet {

    private SchoolService processingService = new SchoolServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String param = req.getParameter("school");
            if (param != null) {
                resp.setCharacterEncoding("UTF-8");
                PrintWriter pw = resp.getWriter();
                int id = Integer.valueOf(req.getParameter("school"));
                List<SchoolClass> classes = processingService.getClassesBySchoolId(id);
                classes.stream().forEach((s) -> pw.println(getLiString(s, id)));
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (NumberFormatException | SchoolDAOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private String getLiString(SchoolClass schoolClass, int id) {
        return "<div class='row'><div class='col-sm-4 col-md-4 col-xs-4'><li>" + schoolClass.getName()
                + "</div>"
                + "<div class='col-sm-8 col-md-8 col-xs-8'>"
                + getHrefString(schoolClass.getId(), id)
                + "</div></li></div>";
    }

    private String getHrefString(int classId, int schoolId) {
        return "<span><a href='" + DEPLOY_PATH + REG_LINK_PAGE
                + "?role=student&class=" + classId
                + "&school=" + schoolId
                + "'>Регистрация учеников</a></span>";
    }
}

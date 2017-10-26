package servlets.admin.lists;

import classes.SchoolClass;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ClassesListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ClassesListServlet.class);
    private SchoolService processingService = new SchoolServiceImpl();

    @RequestMapping(value = "/admin/classes", method = RequestMethod.GET)
    public void doGet(@RequestParam("school") String pSchoolId, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        try {
            PrintWriter pw = resp.getWriter();
            List<SchoolClass> classes = processingService.getClassesBySchoolId(Integer.valueOf(pSchoolId));
            classes.stream().forEach((s) -> pw.println("<li>" + s.getName() + "</li>"));

        } catch (NumberFormatException | SchoolDAOException | IOException e) {
            logger.error(e);
        }
    }
}

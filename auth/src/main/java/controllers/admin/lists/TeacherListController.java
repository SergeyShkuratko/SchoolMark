package controllers.admin.lists;

import exceptions.SchoolTeacherDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import services.TeacherService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class TeacherListServlet{
    private static Logger logger = Logger.getLogger(TeacherListServlet.class);
    private TeacherService processingService;

    @Autowired
    public void setProcessingService(TeacherService processingService) {
        this.processingService = processingService;
    }


    @RequestMapping(value = "/admin/teachers", method = RequestMethod.GET)
    public void doGet(@RequestParam("school") String pSchoolId, HttpServletResponse resp){
        try {
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pw = resp.getWriter();
            int SchoolId = Integer.valueOf(pSchoolId);
            List<String> teacherNames = processingService.getTeacherNamesBySchoolId(SchoolId);
            teacherNames.stream().forEach((t) -> pw.println("<li>" + t + "</li>"));
        } catch (IOException | SchoolTeacherDAOException e) {
            logger.error(e);
        }
    }
}

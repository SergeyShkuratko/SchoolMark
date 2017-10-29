package servlets.admin.lists;

import classes.SchoolClass;
import exceptions.SchoolDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClassesListServlet extends HttpServlet {

    private SchoolService processingService;

    @Autowired
    public void setProcessingService(SchoolService processingService) {
        this.processingService = processingService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String param = req.getParameter("school");
            if (param != null) {
                resp.setCharacterEncoding("UTF-8");
                PrintWriter pw = resp.getWriter();
                int id = Integer.valueOf(req.getParameter("school"));
                List<SchoolClass> classes = processingService.getClassesBySchoolId(id);
                classes.stream().forEach((s) -> pw.println("<li>" + s.getName() + "</li>"));
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (NumberFormatException | SchoolDAOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

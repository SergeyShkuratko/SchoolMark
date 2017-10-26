package servlets.admin.lists;

import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.SchoolService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SchoolListServlet extends HttpServlet {

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
        if (req.getParameter("city") != null) {
            try {
                int cityId = Integer.parseInt(req.getParameter("city"));
                resp.setCharacterEncoding("UTF-8");
                if (cityId > 0) {
                    PrintWriter pw = resp.getWriter();
                    List<SchoolDTO> schools = processingService.getSchoolsByCityId(cityId);
                    schools.forEach((s) -> pw.println("<option value='" + s.id + "'>" + s.name + "</option>"));
                }
                //TODO насколько правильно перехватывать, а не проверять?
            } catch (NumberFormatException | SchoolDAOException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

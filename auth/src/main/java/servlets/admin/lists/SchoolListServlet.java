package servlets.admin.lists;

import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class SchoolListServlet {
    private SchoolService processingService = new SchoolServiceImpl();
    private static Logger logger = Logger.getLogger(SchoolListServlet.class);

    @RequestMapping(value = "/admin/schools", method = RequestMethod.GET)
    public void doGet(@RequestParam("city") String pCityId, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        try {
            int cityId = Integer.parseInt(pCityId);
            if (cityId > 0) {
                PrintWriter pw = resp.getWriter();
                List<SchoolDTO> schools = processingService.getSchoolsByCityId(cityId);
                schools.stream().forEach((s) -> pw.println("<option value='" + s.id + "'>" + s.name + "</option>"));
            }
        } catch (IOException | NumberFormatException | SchoolDAOException e) {
            logger.error(e);
        }

    }
}

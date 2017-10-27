package controllers.admin;

import classes.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.RegistrationUrlService;
import services.impl.RegistrationUrlServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
public class RegistrationUrlServlet {
    Logger logger = Logger.getLogger(RegistrationUrlServlet.class);
    private static RegistrationUrlService registrationUrlService = new RegistrationUrlServiceImpl();

    @RequestMapping(value = "/admin/regUrl", method = RequestMethod.POST)
    public void doPost(@RequestParam("role") String pRole,
                       @RequestParam("schoolId") String pSchoolId,
                       @RequestParam("schoolClassId") String pSchoolClassId,
                       HttpServletResponse resp){
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        if (pRole != null && pSchoolId != null && pSchoolClassId != null) {
            try {
                Role role = Role.valueOf(pRole);
                int schoolId = Integer.parseInt(pSchoolId);
                int schoolClassId = Integer.parseInt(pSchoolClassId);
                resp.getWriter().print(registrationUrlService.generateUrl(role, schoolId, schoolClassId));
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}

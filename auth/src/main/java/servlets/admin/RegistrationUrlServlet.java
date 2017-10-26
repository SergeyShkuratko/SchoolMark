package servlets.admin;

import classes.Role;
import services.RegistrationUrlService;
import services.impl.RegistrationUrlServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationUrlServlet extends HttpServlet {
    private static RegistrationUrlService registrationUrlService = new RegistrationUrlServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pRole = req.getParameter("role");
        String pSchoolId = req.getParameter("schoolId");
        String pSchoolClassId = req.getParameter("schoolClassId");
        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        if (pRole != null && pSchoolId != null && pSchoolClassId != null) {
            try {
                Role role = Role.valueOf(pRole);
                int schoolId = Integer.parseInt(pSchoolId);
                int schoolClassId = Integer.parseInt(pSchoolClassId);
                resp.getWriter().print(registrationUrlService.generateUrl(role, schoolId, schoolClassId));
            } catch (IllegalArgumentException e) {
            }
        }
    }
}

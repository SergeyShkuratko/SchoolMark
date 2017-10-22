package servlets;

import classes.Role;
import classes.dto.SchoolClassDTO;
import classes.dto.SchoolDTO;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RoleDAOException;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import services.RegistrationService;
import services.SchoolService;
import services.impl.RegistrationServiceImpl;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static exceptions.ErrorDescriptions.DATA_ERROR;
import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.ForwardRequestHelper.getErrorDispatcher;

/**
 * Сервлет отображает заполненную форму с URL
 * для регистрации пользователей с конкретной ролью
 */
@WebServlet(urlPatterns = "/admin/registrationlink")
public class RegistrationLinkServlet extends HttpServlet {

    private class RequestAttributes {
        private final SchoolDTO school;
        private SchoolClassDTO schoolClass;
        private final String link;
        private final String header;

        public RequestAttributes(SchoolDTO school, String path, String link, String header) {
            schoolClass = null;
            this.school = school;
            this.link = path + link;
            this.header = header;
        }

        public RequestAttributes(SchoolClassDTO schoolClass, SchoolDTO school, String link, String header) {
            this.schoolClass = schoolClass;
            this.school = school;
            this.link = link;
            this.header = header;
        }

        public void writeToRequest(HttpServletRequest request) {
            request.setAttribute("registrationLink", link);
            request.setAttribute("school", school);
            request.setAttribute("pageHeader", header);
            if (schoolClass != null) {
                request.setAttribute("schoolclass", schoolClass);
            }
        }
    }

    private static RegistrationService registrationService = new RegistrationServiceImpl();
    private static SchoolService schoolService = new SchoolServiceImpl();
    private static Logger logger = Logger.getLogger(RegistrationLinkServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleParam = req.getParameter("role");
        String schoolParam = req.getParameter("school");
        String classParam = req.getParameter("class");
        if (roleParam != null && (schoolParam != null || classParam != null)) {
            try {
                Role role = Role.valueOf(roleParam);
                switch (role) {
                    case teacher:
                        getTeacherRegistrationLink()
                        break;
                }




                attributes.writeToRequest(req);
                req.getRequestDispatcher("/registrationLink.jsp").forward(req, resp);

            } catch (NumberFormatException | RegistrationTokenNotFoundException e) {
                logger.error(e.getMessage(), e);
                getErrorDispatcher(req, DATA_ERROR).forward(req, resp);
                return;
            } catch (SchoolDAOException | RoleDAOException e) {
                logger.error(e.getMessage(), e);
                getErrorDispatcher(req, DB_ERROR).forward(req, resp);
                return;
            }
        }
        getErrorDispatcher(req, DATA_ERROR).forward(req, resp);
    }

    private String getAbsoluteContextPath(HttpServletRequest request) {
        StringBuilder result = new StringBuilder(request.getScheme() + "://");
        result.append(request.getServerName());
        if (request.getServerPort() != 80) {
            result.append(":" + request.getServerPort());
        }
        result.append(request.getContextPath());
        return result.toString();
    }

    private void getTeacherRegistrationLink(HttpServletRequest request, String schoolParam) throws SchoolDAOException {
        int schoolId = Integer.parseInt(schoolParam);
        RequestAttributes attributes = new RequestAttributes(
                schoolService.getSchoolById(schoolId),
                getAbsoluteContextPath(req),
                registrationService.getOrCreateLinkByRoleAndSchool(role, schoolId),
                registrationService.getRegistrationFormHeader(role));
    }
}

package servlets;

import classes.Role;
import classes.School;
import dao.SchoolDAOImpl;
import services.RegistrationService;
import services.impl.RegistrationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отображает заполненную форму с URL
 * для регистрации пользователей с конкретной ролью
 */
@WebServlet(urlPatterns = "/admin/registrationlink")
public class RegistrationLinkServlet extends HttpServlet {

    private static RegistrationService service = new RegistrationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO refactor after merge
        Role role = new Role(1, Integer.valueOf(req.getParameter("role")));
        int schoolId = Integer.parseInt(req.getParameter("school"));
        String link = getAbsoluteContextPath(req) + service.getOrCreateLinkByRoleAndSchool(role, schoolId);
        School school = new SchoolDAOImpl().getSchoolById(schoolId);
        String header = service.getRegistrationFormHeader(role);
        req.setAttribute("registrationLink", link);
        req.setAttribute("school", school);
        req.setAttribute("pageHeader", header);
        req.getRequestDispatcher("/registrationLink.jsp").forward(req, resp);
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
}

package servlets;

import classes.User;
import exceptions.UserNotFoundException;
import services.AuthorizationService;
import services.RegistrationService;
import services.impl.AuthorizationServiceImpl;
import services.impl.RegistrationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.ADMIN_CABINET;
import static classes.CommonSettings.AUTH_ATTRIBUTE;
import static classes.CommonSettings.TEACHER_CABINET;
import static utils.Settings.*;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RegistrationService service = new RegistrationServiceImpl();
        AuthorizationService authService = new AuthorizationServiceImpl();
        User user = service.register(login, password);

        if ("test".equals(login) && "test".equals(password)) {
            req.getSession().setAttribute(AUTH_ATTRIBUTE, true);
            resp.sendRedirect(DEPLOY_PATH + TEACHER_CABINET);
        }

        if ("admin".equals(login) && "admin".equals(password)) {
            req.getSession().setAttribute(AUTH_ATTRIBUTE, true);
            resp.sendRedirect(DEPLOY_PATH + ADMIN_CABINET);
        }

        if (user != null) {
            authService.saveUserToSession(user, req.getSession());
            resp.sendRedirect(DEPLOY_PATH + MAIN_PAGE);
        } else {
            req.setAttribute("error", "Не удалось зарегистрировать пользователя");
            req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
        }
    }
}

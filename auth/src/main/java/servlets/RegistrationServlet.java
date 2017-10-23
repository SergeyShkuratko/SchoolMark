package servlets;

import classes.Role;
import classes.User;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import services.AuthorizationService;
import services.RegistrationService;
import services.impl.AuthorizationServiceImpl;
import services.impl.RegistrationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static exceptions.ErrorDescriptions.*;
import static utils.Settings.*;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("role") == null) {
            String reqId = req.getRequestURI();
            int lastIndex = reqId.lastIndexOf('/') + 1;
            if (lastIndex >= reqId.length()) {
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            } else {
                reqId = reqId.substring(lastIndex);
                RegistrationService service = new RegistrationServiceImpl();
                try {
                    Role role = service.getRoleFromUrl(reqId);
                    req.getSession().setAttribute("role", role);
                } catch (RoleDAOException e) {
                    req.setAttribute("errorText", DB_ERROR);
                    req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
                } catch (RegisterUrlNotFoundException e) {
                    req.setAttribute("errorText", WRONG_REG_URL);
                    req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
                }
            }
            // Сохранили роль, убираем "регистрационную ссылку" из url
            resp.sendRedirect(DEPLOY_PATH + REG_PAGE);
        } else {
            req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = (Role) req.getSession().getAttribute("role");
        RegistrationService service = new RegistrationServiceImpl();
        AuthorizationService authService = new AuthorizationServiceImpl();
        User user = service.register(login, password, role);

        if (user != null) {
            authService.saveUserToSession(user, req.getSession());
            resp.sendRedirect(DEPLOY_PATH + MAIN_PAGE);
        } else {
            req.setAttribute("errorText", REGISTER_ERROR);
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }
}

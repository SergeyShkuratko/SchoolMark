package servlets;

import classes.Role;
import classes.User;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.AuthorizationService;
import services.RegistrationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static exceptions.ErrorDescriptions.*;
import static utils.ForwardRequestHelper.getErrorDispatcher;
import static utils.Settings.*;

public class RegistrationServlet extends HttpServlet {
    private RegistrationService registrationService;
    private AuthorizationService authService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setAuthService(AuthorizationService authService) {
        this.authService = authService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("role") == null) {
            String reqId = req.getRequestURI();
            int lastIndex = reqId.lastIndexOf('/') + 1;
            if (lastIndex >= reqId.length()) {
                getErrorDispatcher(req, WRONG_REG_URL).forward(req, resp);
                return;
            } else {
                reqId = reqId.substring(lastIndex);
                try {
                    Role role = registrationService.getRoleFromUrl(reqId);
                    req.getSession().setAttribute("role", role);
                } catch (RoleDAOException e) {
                    getErrorDispatcher(req, DB_ERROR).forward(req, resp);
                    return;
                } catch (RegisterUrlNotFoundException e) {
                    getErrorDispatcher(req, WRONG_REG_URL).forward(req, resp);
                    return;
                }
            }
            // Сохранили роль, убираем токен из url
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
        if (login != null && password != null && role != null) {
            User user = null;
            try {
                user = registrationService.register(login, password, role);
                if (user != null) {
                    authService.saveUserToSession(user, req.getSession());
                    resp.sendRedirect(DEPLOY_PATH + MAIN_PAGE);
                }
            } catch (UserDAOException e) {
                getErrorDispatcher(req, REGISTER_ERROR).forward(req, resp);
            }
        } else {

        }
    }

}

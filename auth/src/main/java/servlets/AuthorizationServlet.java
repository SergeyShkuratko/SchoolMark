package servlets;

import classes.User;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.AuthorizationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.*;
import static exceptions.ErrorDescriptions.DB_ERROR;
import static exceptions.ErrorDescriptions.USER_NOT_FOUND;
import static utils.ForwardRequestHelper.getErrorDispatcher;
import static utils.Settings.AUTH_JSP;
import static utils.Settings.DEPLOY_PATH;

public class AuthorizationServlet extends HttpServlet {
    public AuthorizationService authService;

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
        req.getRequestDispatcher(AUTH_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null) {
            resp.sendRedirect(DEPLOY_PATH + AUTH_URL);
        }
        User user = null;

        try {
            user = authService.auth(login, password);
        } catch (UserNotFoundException e) {
            getErrorDispatcher(req, USER_NOT_FOUND).forward(req, resp);
            return;
        } catch (UserDAOException e) {
            getErrorDispatcher(req, DB_ERROR).forward(req, resp);
            return;
        }
        if (user != null) {
            req.getSession().setAttribute(AUTH_USER_ATTRIBUTE, user.getUserId());
            req.getSession().setAttribute(AUTH_ROLE_ATTRIBUTE, user.getRole());
            resp.sendRedirect(DEPLOY_PATH + authService.getCabinetUrl(user));
        }
    }
}

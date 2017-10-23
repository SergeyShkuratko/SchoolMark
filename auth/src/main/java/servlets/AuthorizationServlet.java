package servlets;

import classes.User;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import services.AuthorizationService;
import services.impl.AuthorizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.*;
import static exceptions.ErrorDescriptions.*;
import static utils.ForwardRequestHelper.getErrorDispatcher;
import static utils.Settings.*;

public class AuthorizationServlet extends HttpServlet {

    public static AuthorizationService authService = new AuthorizationServiceImpl();

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

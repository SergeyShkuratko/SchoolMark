package servlets;

import classes.User;
import services.RegistrationService;
import services.impl.RegistrationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        //User user = service.register(login, password);
        //if (user != null) {
        if ("test".equals(login)&&"test".equals(password)) {
            //req.getSession().setAttribute(AUTH_ATTRIBUTE, user);
            req.getSession().setAttribute(AUTH_ATTRIBUTE, 1);
            resp.sendRedirect(DEPLOY_PATH + MAIN_PAGE);
        } else {
            req.setAttribute("error", "Не удалось зарегистрировать пользователя");
            req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
        }
    }
}

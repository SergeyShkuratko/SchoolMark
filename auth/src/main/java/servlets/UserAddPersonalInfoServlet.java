package servlets;

import classes.Role;
import classes.School;
import classes.SchoolClass;
import classes.dto.SchoolDTO;
import classes.dto.TokenSavedInfo;
import exceptions.RegistrationTokenDAOException;
import exceptions.RegistrationTokenNotFoundException;
import org.apache.log4j.Logger;
import services.RegistrationService;
import services.SchoolService;
import services.impl.RegistrationServiceImpl;
import services.impl.SchoolServiceImpl;
import utils.ForwardRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static exceptions.ErrorDescriptions.WRONG_REG_URL;
import static utils.ForwardRequestHelper.getErrorDispatcher;

public class UserAddPersonalInfoServlet extends HttpServlet {

    private static SchoolService schoolService = new SchoolServiceImpl();
    private static RegistrationService registrationService = new RegistrationServiceImpl();
    private static Logger logger = Logger.getLogger(UserAddPersonalInfoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (token != null) {
            try {
                TokenSavedInfo info = registrationService.getSavedInfoForToken(token);
                req.setAttribute("tokeninfo", info);
            } catch (RegistrationTokenNotFoundException e) {
                logger.error(e.getMessage(), e);
                getErrorDispatcher(req, WRONG_REG_URL).forward(req, resp);
            } catch (RegistrationTokenDAOException e) {
                logger.error(e.getMessage(), e);
                getErrorDispatcher(req, DB_ERROR).forward(req, resp);
            }
            //TODO load school info

        }

    }
}

package servlets.admin;

import classes.dto.SchoolDTO;
import exceptions.SchoolClassDAOException;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import services.SchoolClassService;
import services.SchoolService;
import services.impl.SchoolClassServiceImpl;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static classes.CommonSettings.*;
import static exceptions.ErrorDescriptions.*;
import static utils.ForwardRequestHelper.getErrorDispatcher;
import static utils.Settings.*;

public class CreateClassServlet extends HttpServlet {

    private static SchoolClassService classService = new SchoolClassServiceImpl();
    private static SchoolService schoolService = new SchoolServiceImpl();
    private static Logger logger = Logger.getLogger(CreateClassServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String schoolId = req.getParameter("school");
        if (schoolId != null) {
            try {
                SchoolDTO school = schoolService.getSchoolById(Integer.valueOf(schoolId));
                req.setAttribute("school", school);
                req.getRequestDispatcher(CLASS_JSP).forward(req, resp);
            } catch (ClassCastException | SchoolDAOException e) {
                logger.error(e.getMessage(), e);
                getErrorDispatcher(req, DB_ERROR).forward(req, resp);
            }
        } else {
            logger.info("Parameters not found");
            getErrorDispatcher(req, DATA_ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("school") != null
                && req.getParameter("class_num") != null
                && req.getParameter("letter") != null) {
            int schoolId = Integer.valueOf(req.getParameter("school"));
            int classNum = Integer.valueOf(req.getParameter("class_num"));
            String letter = req.getParameter("letter");

            try {
                if (classService.add(schoolId, classNum, letter)) {
                    resp.sendRedirect(DEPLOY_PATH + ADMIN_CABINET);
                } else {
                    getErrorDispatcher(req, DB_ERROR).forward(req, resp);
                }
            } catch (SchoolClassDAOException e) {
                getErrorDispatcher(req, DB_ERROR).forward(req, resp);
            }

        }
    }
}

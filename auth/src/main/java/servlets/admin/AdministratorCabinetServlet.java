package servlets.admin;

import classes.School;
import dao.SchoolDAOImpl;
import exceptions.SchoolDAOException;
import interfaces.dao.SchoolDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.Settings.*;

public class AdministratorCabinetServlet extends HttpServlet {

    SchoolDAO schoolDAO = new SchoolDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<School> schools = schoolDAO.getAll();
            req.setAttribute("schools", schools);
            req.getRequestDispatcher("/cabinet.jsp").forward(req, resp);
        } catch (SchoolDAOException e) {
            //TODO уточнить про логирование, если оно идет уровнем ниже
            req.setAttribute(ERROR_ATTR, DB_ERROR);
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }

    }
}

package servlets.admin;

import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.ForwardRequestHelper.getErrorDispatcher;
import static utils.Settings.*;

@WebServlet("/admin/cabinet")
public class AdministratorCabinetServlet extends HttpServlet {

    private static SchoolService service = new SchoolServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<SchoolDTO> schools = service.getAllSchools();
            req.setAttribute("schools", schools);
            req.getRequestDispatcher(CABINET_JSP).forward(req, resp);
        } catch (SchoolDAOException e) {
            //TODO уточнить про логирование, если оно идет уровнем ниже
            getErrorDispatcher(req, DB_ERROR).forward(req, resp);
        }
    }
}

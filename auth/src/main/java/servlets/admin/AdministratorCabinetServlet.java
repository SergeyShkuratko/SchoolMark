package servlets.admin;

import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.SchoolService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.ForwardRequestHelper.getErrorDispatcher;
import static utils.Settings.*;

public class AdministratorCabinetServlet extends HttpServlet {
    private SchoolService schoolService;

    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<SchoolDTO> schools = schoolService.getAllSchools();
            req.setAttribute("schools", schools);
            req.getRequestDispatcher(CABINET_JSP).forward(req, resp);
        } catch (SchoolDAOException e) {
            //TODO уточнить про логирование, если оно идет уровнем ниже
            getErrorDispatcher(req, DB_ERROR).forward(req, resp);
        }
    }
}

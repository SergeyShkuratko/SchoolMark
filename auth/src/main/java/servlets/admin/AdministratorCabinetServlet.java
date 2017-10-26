package servlets.admin;

import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.SchoolService;
import services.impl.SchoolServiceImpl;

import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.Settings.*;
@Controller
public class AdministratorCabinetServlet {
    private static Logger logger = Logger.getLogger(AdministratorCabinetServlet.class);
    private static SchoolService service = new SchoolServiceImpl();

    @RequestMapping("/admin/cabinet")
    public ModelAndView doGet(){
        ModelAndView mv = new ModelAndView();
        try {
            List<SchoolDTO> schools = service.getAllSchools();
            mv.addObject("schools", schools);
            mv.setViewName(CABINET_JSP);
        } catch (SchoolDAOException e) {
            logger.error(e);
            mv.addObject(ERROR_ATTR, DB_ERROR);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }
}

package controllers.admin;

import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import services.SchoolService;

import java.util.List;

import static exceptions.ErrorDescriptions.DB_ERROR;
import static utils.Settings.*;
@Controller
public class AdministratorCabinetController {
    private static Logger logger = Logger.getLogger(AdministratorCabinetController.class);
    private SchoolService schoolService;

    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @RequestMapping("/admin/cabinet")
    public ModelAndView doGet(){

        ModelAndView mv = new ModelAndView();
        try {
            List<SchoolDTO> schools = schoolService.getAllSchools();
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

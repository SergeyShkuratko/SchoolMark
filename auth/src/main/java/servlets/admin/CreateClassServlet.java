package servlets.admin;

import classes.dto.SchoolDTO;
import exceptions.SchoolClassDAOException;
import exceptions.SchoolDAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import services.SchoolClassService;
import services.SchoolService;

import static classes.CommonSettings.*;
import static exceptions.ErrorDescriptions.*;
import static utils.Settings.*;
@Controller
public class CreateClassServlet {

    private static Logger logger = Logger.getLogger(CreateClassServlet.class);
	private SchoolClassService classService;
    private SchoolService schoolService;

    @Autowired
    public void setClassService(SchoolClassService classService) {
        this.classService = classService;
    }

    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(value = "/admin/class", method = RequestMethod.GET)
    public ModelAndView doGet(@RequestParam("school") String pSchoolId){
        ModelAndView mv = new ModelAndView();
        try {
            SchoolDTO school = schoolService.getSchoolById(Integer.valueOf(pSchoolId));
            mv.addObject("school", school);
            mv.setViewName(CLASS_JSP);
        } catch (ClassCastException | SchoolDAOException e) {
            logger.error(e.getMessage(), e);
            mv.addObject(ERROR_ATTR, DB_ERROR);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }

    @RequestMapping(value = "/admin/class", method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam("school") String pSchoolId, @RequestParam("class_num") String pClassNum,
                            @RequestParam("letter") String pLetter){
        ModelAndView mv = new ModelAndView();
        int schoolId = Integer.valueOf(pSchoolId);
        int classNum = Integer.valueOf(pClassNum);
        try {
            if (classService.add(schoolId, classNum, pLetter)) {
                mv.setViewName("redirect: "+DEPLOY_PATH + ADMIN_CABINET);
            } else {
                mv.addObject(ERROR_ATTR, DB_ERROR);
                mv.setViewName(ERROR_JSP);
            }
        } catch (SchoolClassDAOException e) {
            mv.addObject(ERROR_ATTR, DB_ERROR);
            mv.setViewName(ERROR_JSP);
        }
        return mv;
    }
}

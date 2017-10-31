package studentmodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import security.CustomUser;
import studentmodule.exception.DAOStudentWorkException;
import studentmodule.service.WorkService;

import static studentmodule.constants.ControllersConstants.RESPONSE_ERROR_MESSAGE;

@Controller
public class TestListServlet {

    private final Logger logger = Logger.getLogger(TestListServlet.class);

    private WorkService workService;

    @Autowired
    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public String showHelloWithData(ModelMap model) {
        int userId = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("have_new_test", true);
        try {
            model.addAttribute("works", workService.getAllWork(userId));
        } catch (DAOStudentWorkException e) {
            logger.error(e.getMessage(), e);
            model.addAttribute("whatHappened",
                    RESPONSE_ERROR_MESSAGE + e.getMessage());
            return "WEB-INF/pages/errpages/500-error";
        }
        return "WEB-INF/pages/student/testlist";
    }

}

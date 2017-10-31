package inno.controllers;

import inno.dao.OrganizerDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nkm on 26.10.2017.
 */
@Controller
public class NewTestController {

    private Logger logger;

    private final OrganizerDAO organizerDAO;

    @Autowired
    public NewTestController(OrganizerDAO organizerDAO) {
        this.organizerDAO = organizerDAO;
        logger = Logger.getLogger(NewTestController.class);
    }

    @RequestMapping(value = "/test-start", method = RequestMethod.GET) //test-start?test_id=15
    protected ModelAndView startWork(@RequestParam("test_id") Integer testId){

//      testId = testId != null ? testId : 0;
        ModelAndView modelAndView = new ModelAndView("test_start");
        if (testId > 0) {
            try {
                //получаем тест из БД
                TestDTO test = organizerDAO.getTestById(testId);
                //добавляем тест
                modelAndView.addObject("test", test);
                if ("new".equals(test.getStatus())) {
                    modelAndView.addObject("test", test);
                    return modelAndView;
                } else {
                    return new ModelAndView("redirect:/test-run?test_id=" + test.getId());
                }
            } catch (OrganizerDAOexception e) {
                logger.error(e);
            }
        }
        return null;
    }
}

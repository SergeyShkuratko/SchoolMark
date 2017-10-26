package inno.controllers;

import inno.dao.OrganizerDAO;
import inno.dto.TestDTO;
import inno.exceptions.OrganizerDAOexception;
import inno.service.StopTestService;
import inno.servlets.NewTestServlet;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nkm on 27.10.2017.
 */
@Controller
public class StopTestController {
    private static Logger logger = Logger.getLogger(NewTestServlet.class);

    @RequestMapping(value = "/test-stop", method = RequestMethod.GET)
    protected String stopWork(@RequestParam("test_id") Integer testId){

//      testId = testId != null ? testId : 0;
        if (StopTestService.stopTest(testId)) {
            return "test_stop";
        }

        return null;
    }

}

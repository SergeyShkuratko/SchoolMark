package inno.controllers;

import inno.service.StatusTestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nkm on 27.10.2017.
 */
@Controller
public class StopTestController {

    private static Logger logger;

    private final StatusTestService StatusTestService;

    @Autowired
    public StopTestController(StatusTestService StatusTestService) {
        this.StatusTestService = StatusTestService;
        logger = Logger.getLogger(StopTestController.class);
    }


    @RequestMapping(value = "/test-stop", method = RequestMethod.GET)
    protected String stopWork(@RequestParam("test_id") Integer testId){

//      testId = testId != null ? testId : 0;
        if (StatusTestService.stopTest(testId)) {
            return "test_stop";
        }

        return null;
    }

}

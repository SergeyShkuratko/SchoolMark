package controller.controllers;

import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import controller.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/getInfo")
@Controller

public class InfoController {
    private static final Logger logger = Logger.getLogger(InfoController.class);
    TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public @ResponseBody String getInfo(@RequestParam(name = "getWorksForTest", required = false) Integer testId,
                          @RequestParam(name = "getImageForWork", required = false) Integer workId) {
        if (testId != null) {
            return testService.getTestInfoJsonByTestId(testId);
        }
        if (workId != null) {
            return testService.getWorkPagesJsonByWorkId(workId);
        }
        return "";
    }
}

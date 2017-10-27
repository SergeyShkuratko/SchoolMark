package controller.controllers;

import classes.CommonSettings;
import controller.dao.dto.TestsDTO;
import controller.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class StartController extends HttpServlet {

    private TestService testService;

/*    @ModelAttribute("userId")
    public Integer userIdFromSession() {
    }*/

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ModelAndView start(HttpSession session) {
        //FOR TEST ONLY!!!
        // int userId = (Integer) req.getSession().getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);

        int userId = (int) session.getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);

        List<TestsDTO> testService = this.testService.getWorksForVerifier(userId);

        ModelAndView mav = new ModelAndView("controller.jsp");

        mav.addObject("tests", testService);
        return mav;
    }

}

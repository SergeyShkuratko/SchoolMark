package controller.servlets;

import classes.CommonSettings;
import controller.dao.dto.TestsDTO;
import controller.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import controller.service.TestServiceImpl;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private  TestService testService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }



    @Override

   // @RequestMapping(value = "/start", method = RequestMethod.GET)

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //FOR TEST ONLY!!!
        // int userId = (Integer) req.getSession().getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);

        int userId = 1;

        List<TestsDTO> testService = this.testService.getWorksForVerifier(userId);
        req.setAttribute("tests", testService);
        req.getRequestDispatcher("/controller.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

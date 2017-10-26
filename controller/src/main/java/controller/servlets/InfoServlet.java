package controller.servlets;

import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import controller.service.TestService;
import controller.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("ALL")
@WebServlet("/getInfo")
public class InfoServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(InfoServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    TestService testService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer testId = getParameterByName(req, "getWorksForTest");
        if (testId != null) {
            String json = testService.getTestInfoJsonByTestId(testId);
            resp.getWriter().print(json);
            return;
        }

        Integer workId = getParameterByName(req, "getImageForWork");
        if (workId != null) {
            String json = testService.getWorkPagesJsonByWorkId(workId);
            resp.getWriter().print(json);
            return;
        }
    }

    private Integer getParameterByName(HttpServletRequest req, String name) {
        String parameter = req.getParameter(name);
        if (Strings.isNullOrEmpty(parameter)) {
            return null;
        }
        Integer result = null;
        try {
            result = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }


}

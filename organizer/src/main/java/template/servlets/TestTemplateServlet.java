package template.servlets;

import template.dao.TestDAOImplementation;
import template.dto.Test;
import template.dto.TestTemplate;
import template.dto.TestVariant;
import template.services.TestTemplateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by nkm on 13.10.2017.
 */
public class TestTemplateServlet extends HttpServlet {
    public static TestTemplateService testTemplateService = new TestTemplateService();
    public TestDAOImplementation testDAOImplementation = new TestDAOImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/test-template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        if (req.getSession().getAttribute("templateFirstPost") != null) {

            List<TestVariant> testVariants = testTemplateService.getTestVariantsFromReq(req);
            Test test = (Test) req.getSession().getAttribute("test");
            test.getTestTemplate().getTestVariants().addAll(testVariants);
            test.getTestTemplate().setTopic(req.getParameter("templateTopic"));

            testDAOImplementation.createTest(test);
            getServletContext().getRequestDispatcher("/template-list").forward(req, resp);

        } else if (req.getSession().getAttribute("testTemplate") != null) {
            List<TestVariant> variants = ((TestTemplate) req.getSession().
                    getAttribute("testTemplate")).getTestVariants();
            req.setAttribute("variants", variants);
            getServletContext().getRequestDispatcher("/test-template-loaded.jsp").forward(req, resp);

        } else {

            req.getSession().setAttribute("templateFirstPost", false);
            getServletContext().getRequestDispatcher("/test-template.jsp").forward(req, resp);
        }
    }
}

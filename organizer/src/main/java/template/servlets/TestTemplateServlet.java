package template.servlets;

import template.dto.Test;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/test-template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TestVariant> testVariants = testTemplateService.getTestVariantsFromReq(req);
        Test test = (Test)req.getAttribute("test");
//        test.getTestVariants().addAll(testVariants);

        //TestDAOImplementation.createTest(test);
    }
}

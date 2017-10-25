package template.servlets;

import template.dao.TestDAOImplementation;
import template.dao.TestTemplateDAOImplementation;
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
        TestTemplate testTemplate = (TestTemplate) req.getSession().getAttribute("testTemplate");

        if (testTemplate != null) {
            req.setAttribute("testTemplate", testTemplate);
        }

        Boolean templateSaved = (Boolean) req.getSession().getAttribute("templateSaved");
        if (templateSaved != null) {
            req.setAttribute("templateSaved", templateSaved);
        }

        getServletContext().getRequestDispatcher("/test-template.jsp").forward(req, resp);
        req.getSession().setAttribute("templateSaved", false);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        switch (req.getParameter("templateFormButton")){
            case "endInputQuestions":
                proceedToTestInput(req, resp);
                break;
            case "saveAsTemplate":
                saveAsTemplate(req, resp);
                break;
            case "saveChanges":
                saveChanges(req,resp);
                break;
            case "returnWithoutSave":
                returnWithoutSave(req, resp);
                break;
            case "cancel":
                cancel(req, resp);
                break;
        }
    }

    private void saveChanges(HttpServletRequest req, HttpServletResponse resp) {

        TestTemplate oldTemplate = (TestTemplate) req.getSession().getAttribute("testTemplate");
        TestTemplate newTemplate = createTemplateFromPrototype(req);

        if (oldTemplate.sameAs(newTemplate)){
            //тогда ничего не делаем, просто редиректим
            try {
                resp.sendRedirect(getServletContext().getContextPath() + "/test");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {

            TestTemplateDAOImplementation.setStatusDisabled(oldTemplate);
            newTemplate.setId(TestTemplateDAOImplementation.createTestTemplateCascade(newTemplate));
            req.getSession().setAttribute("testTemplate", newTemplate);
            try {
                resp.sendRedirect(getServletContext().getContextPath() + "/test");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void returnWithoutSave(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect(getServletContext().getContextPath() + "/test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAsTemplate(HttpServletRequest req, HttpServletResponse resp) {
        TestTemplate testTemplate = createTemplateFromPrototype(req);

        testTemplate.setId(TestTemplateDAOImplementation.createTestTemplateCascade(testTemplate));
        req.getSession().setAttribute("testTemplate", testTemplate);

        req.getSession().setAttribute("templateSaved", true);
        try {
            resp.sendRedirect(getServletContext().getContextPath() + "/test-template");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void proceedToTestInput(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("testTemplate", createTemplateFromPrototype(req));
        try {
            resp.sendRedirect(getServletContext().getContextPath() + "/test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TestTemplate createTemplateFromPrototype(HttpServletRequest req) {
        TestTemplate testTemplate = (TestTemplate) req.getSession().getAttribute("templatePrototype");
        if (testTemplate == null) {
            testTemplate = new TestTemplate();
        }
        List<TestVariant> testVariants = testTemplateService.getTestVariantsFromReq(req);
        testTemplate.getTestVariants().addAll(testVariants);
        return testTemplate;
    }

    private void cancel(HttpServletRequest req, HttpServletResponse resp) {
        try {
            getServletContext().getRequestDispatcher("/test-template.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

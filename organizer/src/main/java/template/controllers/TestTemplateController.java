package template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import template.dto.TestTemplate;
import template.dto.TestVariant;
import template.services.TestTemplateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestTemplateController {

    private final TestTemplateService testTemplateService;

    @Autowired
    public TestTemplateController(TestTemplateService testTemplateService) {
        this.testTemplateService = testTemplateService;
    }

    @RequestMapping(value = "/test-template", method = RequestMethod.GET)
    public ModelAndView getTestTemplateGet(HttpSession session) {
        Map<String, Object> model = new HashMap<>();

        TestTemplate testTemplate = (TestTemplate) session.getAttribute("testTemplate");
        model.put("testTemplate", testTemplate);

        Boolean templateSaved = (Boolean) session.getAttribute("templateSaved");
        model.put("templateSaved", templateSaved);

        session.setAttribute("templateSaved", false);

        return new ModelAndView("test-template", model);
    }

    @RequestMapping(value = "/test-template", method = RequestMethod.POST)
    public String getTestTemplatePost(HttpServletRequest request, HttpSession session,
                                      @RequestParam(name = "templateFormButton") String pressedButton) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");

        switch (pressedButton) {
            case "endInputQuestions":
                session.setAttribute("testTemplate", createTemplateFromPrototype(request));
                return "redirect:/test";

            case "saveAsTemplate":
                saveAsTemplate(request, session);
                return "redirect:/test-template";

            case "saveChanges":
                saveChanges(request, session);
                return "redirect:/test";

            case "returnWithoutSave":
                return "redirect:/test";

            case "cancel":
                return "forward:/test-template.jsp";
        }
        throw new IllegalArgumentException("Request is not contain correct parameter");
    }

    private void saveAsTemplate(HttpServletRequest request, HttpSession session) {
        TestTemplate testTemplate = createTemplateFromPrototype(request);
        testTemplate.setId(testTemplateService.createTestTemplateCascade(testTemplate));
        session.setAttribute("testTemplate", testTemplate);
        session.setAttribute("templateSaved", true);
    }

    private void saveChanges(HttpServletRequest request, HttpSession session) {
        TestTemplate oldTemplate = (TestTemplate) session.getAttribute("testTemplate");
        TestTemplate newTemplate = createTemplateFromPrototype(request);
        if (!oldTemplate.sameAs(newTemplate)) {
            testTemplateService.setStatusDisabled(oldTemplate);
            newTemplate.setId(testTemplateService.createTestTemplateCascade(newTemplate));
            session.setAttribute("testTemplate", newTemplate);
        }
    }

    @SuppressWarnings("Duplicates")
    private TestTemplate createTemplateFromPrototype(HttpServletRequest req) {
        TestTemplate testTemplate = (TestTemplate) req.getSession().getAttribute("templatePrototype");
        if (testTemplate == null) {
            testTemplate = new TestTemplate();
        }
        List<TestVariant> testVariants = testTemplateService.getTestVariantsFromReq(req);
        testTemplate.getTestVariants().addAll(testVariants);
        return testTemplate;
    }
}
package template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import template.dto.Teacher;
import template.dto.Test;
import template.dto.TestTemplate;
import template.dto.TestVariant;
import template.services.TestService;
import template.services.TestTemplateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/test-template")
public class TestTemplateController {

    private final TestTemplateService testTemplateService;
    private final TestService testService;

    @Autowired
    public TestTemplateController(TestTemplateService testTemplateService, TestService testService) {
        this.testTemplateService = testTemplateService;
        this.testService = testService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTestTemplateGet(HttpSession session) {
        Map<String, Object> model = new HashMap<>();

        TestTemplate testTemplate = (TestTemplate) session.getAttribute("testTemplate");
        model.put("testTemplate", testTemplate);

        Boolean templateSaved = (Boolean) session.getAttribute("templateSaved");
        model.put("templateSaved", templateSaved);

        session.setAttribute("templateSaved", false);

        return new ModelAndView("/test-template", model);
    }

    @RequestMapping(params = {"templateFormButton=endInputQuestions"}, method = RequestMethod.POST)
    public String endInputQuestions(HttpServletRequest request, HttpSession session) {
        session.setAttribute("testTemplate", createTemplateFromPrototype(request));
        return "redirect:/test";
    }

    @RequestMapping(params = {"templateFormButton=saveAsTemplate"}, method = RequestMethod.POST)
    public String saveAsTemplate(HttpServletRequest request, HttpSession session) {
        TestTemplate testTemplate = createTemplateFromPrototype(request);
        testTemplate.setId(testTemplateService.createTestTemplateCascade(testTemplate));
        session.setAttribute("testTemplate", testTemplate);
        session.setAttribute("templateSaved", true);
        return "redirect:/test-template";
    }

    @RequestMapping(params = {"templateFormButton=saveChanges"}, method = RequestMethod.POST)
    public String saveChanges(HttpServletRequest request, HttpSession session) {
        TestTemplate oldTemplate = (TestTemplate) session.getAttribute("testTemplate");
        TestTemplate newTemplate = createTemplateFromPrototype(request);
        if (!oldTemplate.sameAs(newTemplate)) {
            testTemplateService.setStatusDisabled(oldTemplate);
            newTemplate.setId(testTemplateService.createTestTemplateCascade(newTemplate));
            session.setAttribute("testTemplate", newTemplate);
        }
        return "redirect:/test";
    }

    @RequestMapping(params = {"templateFormButton=createTest"}, method = RequestMethod.POST)
    public String createTest(HttpServletRequest request, HttpSession session) {
        TestTemplate testTemplate = (TestTemplate) session.getAttribute("testTemplate");
        if (testTemplate == null) {
            testTemplate = createTemplateFromPrototype(request);
            testTemplate.setId(testTemplateService.createTestTemplateCascade(testTemplate));
        } else {
            TestTemplate newTemplate = createTemplateFromPrototype(request);
            if (!testTemplate.sameAs(newTemplate)) {
                testTemplateService.setStatusDisabled(testTemplate);
                newTemplate.setId(testTemplateService.createTestTemplateCascade(newTemplate));
                testTemplate = newTemplate;
            }
        }
        Test test = (Test) session.getAttribute("test");
        test.setTestTemplate(testTemplate);
        testService.createTest(test, (Teacher) session.getAttribute("teacher"));
        return ("redirect:/organizer/calendar");
    }

    @RequestMapping(params = {"templateFormButton=returnWithoutSave"}, method = RequestMethod.POST)
    public String returnWithoutSave() {
        return "redirect:/test";
    }

    @RequestMapping(params = {"templateFormButton=cancel"}, method = RequestMethod.POST)
    public String cancel() {
        return "redirect:/test-template";
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
}
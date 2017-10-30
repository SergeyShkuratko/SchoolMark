package template.controllers;

import classes.Subject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import template.dto.Teacher;
import template.dto.Test;
import template.dto.TestTemplate;
import template.services.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class TestController {
    private static final Logger logger = Logger.getLogger(TestController.class);

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTestServletGet(HttpSession session) {
        Map<String, Object> model = new HashMap<>();

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            userId = 1;
        }

        Teacher teacher = testService.getTeacherByUserId(userId);
        session.setAttribute("teacher", teacher);

        Test test = (Test) session.getAttribute("test");
        model.put("test", test);

        TestTemplate testTemplate = (TestTemplate) session.getAttribute("testTemplate");
        model.put("testTemplate", testTemplate);

        List<Subject> subjects = testService.getAllSubjects();
        model.put("subjects", subjects);

        List<Integer> classNumbers = testService.getClassNumbersByTeacher(teacher);
        model.put("classNumbers", classNumbers);

        List<String> classNames = testService.getClassNamesByTeacher(teacher);
        model.put("classNames", classNames);

        return new ModelAndView("/test", model);
    }

    @RequestMapping(params = {"testFormButton=loadTemplate"}, method = RequestMethod.POST)
    public ModelAndView loadTemplate() {
        return new ModelAndView("redirect:/template-list");
    }

    @RequestMapping(params = {"testFormButton=createTest"}, method = RequestMethod.POST)
    public ModelAndView createTest(HttpServletRequest request) {
        TestTemplate testTemplate = (TestTemplate) request.getSession().getAttribute("testTemplate");
        if (testTemplate == null) {
            request.setAttribute("questionsNotLoaded", true);
            return new ModelAndView("/test");
        } else {
            Test test = testService.getTestFromReq(request);
            test.setTestTemplate(testTemplate);
            testService.createTest(test, (Teacher) request.getSession().getAttribute("teacher"));
            return new ModelAndView("redirect:/organizer/calendar");
        }
    }

    @RequestMapping(params = {"testFormButton=inputQuestions"}, method = RequestMethod.POST)
    public ModelAndView getInputQuestions(HttpServletRequest request) {
        TestTemplate testTemplate = testService.getTestTemplateFromReq(request);
        request.getSession().setAttribute("templatePrototype", testTemplate);
        return new ModelAndView("redirect:/test-template");
    }
}
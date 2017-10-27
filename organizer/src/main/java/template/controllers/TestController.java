package template.controllers;

import classes.CommonSettings;
import classes.Subject;
import classes.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import template.dao.ClassDAOImplementation;
import template.dao.SubjectDAOImplementation;
import template.dao.TeacherDAOImplementation;
import template.dao.TestDAOImplementation;
import template.dto.Teacher;
import template.dto.Test;
import template.dto.TestTemplate;
import template.services.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class TestController {
    private static final Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView getTestServletGet(HttpSession session) {
        Map<String, Object> model = new HashMap<>();

        User user = (User) session.getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);
        Teacher teacher = TeacherDAOImplementation.getTeacherByUser(user);
//        session.setAttribute("teacher", teacher);

        Test test = (Test) session.getAttribute("test");
        model.put("test", test);

        TestTemplate testTemplate = (TestTemplate) session.getAttribute("testTemplate");
        model.put("testTemplate", testTemplate);

        List<Subject> subjects = SubjectDAOImplementation.getAllSubjects();
        model.put("subjects", subjects);

        List<Integer> classNumbers = ClassDAOImplementation.getClassNumbersByTeacher(teacher);
        model.put("classNumbers", classNumbers);

        List<String> classNames = ClassDAOImplementation.getClassNamesByTeacher(teacher);
        model.put("classNames", classNames);

        return new ModelAndView("test", model);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView getTestServletPost(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String testFormButton = request.getParameter("testFormButton");
        switch (testFormButton) {
            case "loadTemplate":
                return new ModelAndView("redirect:/template-list");

            case "createTest":
                return createTest(request);

            case "inputQuestions":
                return inputQuestions(request);
        }

        throw new IllegalArgumentException("Request is not contain correct parameter");
    }

    private ModelAndView createTest(HttpServletRequest request) {
        TestTemplate testTemplate = (TestTemplate) request.getSession().getAttribute("testTemplate");
        if (testTemplate == null) {
            request.setAttribute("questionsNotLoaded", true);
            return new ModelAndView("/test.jsp");
        } else {
            Test test = testService.getTestFromReq(request);
            test.setTestTemplate(testTemplate);
            TestDAOImplementation.createTest(test, (Teacher) request.getSession().getAttribute("teacher"));
            return new ModelAndView("redirect:/organizer/calendar");
        }
    }

    private ModelAndView inputQuestions(HttpServletRequest request) {
        TestTemplate testTemplate = testService.getTestTemplateFromReq(request);
        request.getSession().setAttribute("templatePrototype", testTemplate);
        return new ModelAndView("redirect:/test-template");
    }
}

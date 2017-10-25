package template.controllers;

import classes.CommonSettings;
import classes.Subject;
import classes.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import template.dao.ClassDAOImplementation;
import template.dao.SubjectDAOImplementation;
import template.dao.TeacherDAOImplementation;
import template.dto.Teacher;
import template.dto.Test;
import template.dto.TestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
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
}

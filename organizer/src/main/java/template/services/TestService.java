package template.services;

import classes.SchoolClass;
import classes.Subject;
import org.springframework.stereotype.Service;
import template.dao.ClassDAOImplementation;
import template.dto.Teacher;
import template.dto.Test;
import template.dto.TestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Service
public class TestService {

    public TestTemplate getTestTemplateFromReq(HttpServletRequest req) {
        TestTemplate testTemplate = new TestTemplate();

        testTemplate.setTopic(req.getParameter("templateTopic"));
        testTemplate.setDescription(req.getParameter("testTheme")); //TODO не забыть убрать, если уберем из БД...
        testTemplate.setSubject(new Subject(0, req.getParameter("subject")));
        testTemplate.setClassNum(new Integer(req.getParameter("classNum")));
        testTemplate.setDifficulty(req.getParameter("difficulty"));
        testTemplate.setCreationDate(LocalDate.now());

        return testTemplate;
    }

    public Test getTestFromReq(HttpServletRequest req) {
        Test test = new Test();
        test.setSchoolClass(getSchoolClassFromReq(req));
        test.setTestDescription(req.getParameter("testTheme"));
        test.setTestDate(LocalDate.parse(req.getParameter("testDate")));
        test.setDeadlineDate(LocalDate.parse(req.getParameter("deadlineDate")));

        return test;
    }

    public SchoolClass getSchoolClassFromReq(HttpServletRequest req) {
        TestTemplate testTemplate = (TestTemplate) req.getSession().getAttribute("testTemplate");
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");

        SchoolClass schoolClass = ClassDAOImplementation.getClassByNumAndName(
                testTemplate.getClassNum(),
                req.getParameter("className"),
                teacher.getSchoolId());

        return schoolClass;
    }
}
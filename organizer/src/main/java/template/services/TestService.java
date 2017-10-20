package template.services;

import classes.SchoolClass;
import classes.Subject;
import template.dao.ClassDAOImplementation;
import template.dto.Test;
import template.dto.TestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestService {

    public TestTemplate getTestTemplateFromReq(HttpServletRequest req) {
        TestTemplate testTemplate = new TestTemplate();

        testTemplate.setTopic(req.getParameter("templateTopic"));
        testTemplate.setDescription(req.getParameter("testTheme")); //TODO не забвть убрать, если уберем из БД...
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

        //TODO заменить на ClassDAO.GETClassByNumANdName...
        SchoolClass schoolClass = ClassDAOImplementation.getClassId(
                testTemplate.getClassNum(),
                req.getParameter("className"),
                2);

        return schoolClass;
    }
}

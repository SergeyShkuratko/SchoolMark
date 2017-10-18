package template.services;

import classes.SchoolClass;
import classes.Subject;
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

        testTemplate.setDescription(req.getParameter("testTheme"));
        testTemplate.setSubject(new Subject(1, req.getParameter("subject")));
        testTemplate.setClassNum(new Integer(req.getParameter("classNum")));
        testTemplate.setDifficulty(req.getParameter("difficulty"));
        testTemplate.setCreationDate(LocalDate.now());

        return testTemplate;
    }

    public Test getTestFromReq(HttpServletRequest req) {
        Test test = new Test();

        test.setTestTemplate(getTestTemplateFromReq(req));

        test.setSchoolClass(new SchoolClass( //TODO заменить на ClassDAO.GETClassByNumANdName...
                1,
                new Integer(req.getParameter("classNum")),
                req.getParameter("className")));

        test.setTestDate(LocalDate.parse(req.getParameter("testDate")));
        test.setDeadlineDate(LocalDate.parse(req.getParameter("deadlineDate")));

        return test;
    }
}

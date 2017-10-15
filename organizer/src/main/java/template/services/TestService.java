package template.services;

import template.dto.Test;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestService {
    public Test getTestFromReq(HttpServletRequest req) {
        Test test = new Test();

        test.setTestTheme(req.getParameter("testTheme"));
        test.setSubject(req.getParameter("subject"));
        test.setClassNum(new Integer(req.getParameter("classNum")));
        test.setDifficulty(req.getParameter("difficulty"));
        test.setTestDate(LocalDate.parse(req.getParameter("testDate")));
        test.setDeadlineDate(LocalDate.parse(req.getParameter("deadlineDate")));

        return test;
    }
}

package servlets;

import classes.SchoolClass;
import classes.Subject;
import classes.Test;
import classes.TestTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/start")
public class ControllerStartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Test> tests = new ArrayList<>();
        TestTemplate testTemplate = new TestTemplate(1, "SUPER THEME", "SUPER DESCRIPTION", 1, new Subject(1, "MATHEMATIC"));
        Test test = new Test(1, testTemplate, null, new SchoolClass(1, 2, "A"));
        Test test1 = new Test(2, testTemplate, null, new SchoolClass(2, 1, "Б"));
        Test test2 = new Test(3, testTemplate, null, new SchoolClass(3, 4, "В"));
        test.setStartDate(LocalDate.now());
        test1.setStartDate(LocalDate.now());
        test2.setStartDate(LocalDate.now());
        tests.add(test);
        tests.add(test1);
        tests.add(test2);
        req.setAttribute("tests", tests);
        req.getRequestDispatcher("/controller.jsp").forward(req, resp);
    }
}

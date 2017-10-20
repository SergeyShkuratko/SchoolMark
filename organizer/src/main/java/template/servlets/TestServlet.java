package template.servlets;

import classes.SchoolClass;
import classes.Subject;
import classes.Teacher;
import classes.User;
import template.dao.SubjectDAOImplementation;
import template.dao.TeacherDAOImplementation;
import template.dao.TestDAOImplementation;
import template.dto.Test;
import template.dto.TestTemplate;
import template.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by nkm on 13.10.2017.
 */
public class TestServlet extends HttpServlet {
    public static TestService testService = new TestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Получаю teacherID
        User user = (User) req.getSession().getAttribute("User");
        if(user == null)
        {
            user = new User(1, "1", LocalDate.now());
        }
        int teacherId = TeacherDAOImplementation.getTeacherIdByUser(user);
        req.getSession().setAttribute("teacherId", teacherId);


        Test test = (Test) req.getSession().getAttribute("test");
        if (test != null) {
            req.setAttribute("test", test);
        }

        TestTemplate testTemplate = (TestTemplate) req.getSession().getAttribute("testTemplate");
        if (testTemplate != null) {
            req.setAttribute("testTemplate", testTemplate);
        }

//        List<String> classLetters = SubjectDAOImplementation.getAllSubjects();
//        req.setAttribute("subjects", subjects);

        List<Subject> subjects = SubjectDAOImplementation.getAllSubjects();
        req.setAttribute("subjects", subjects);

        getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        switch (req.getParameter("testFormButton")){
            case "loadTemplate":
                proceedToTemplateLoad(req, resp);
                break;
            case "createTest":
                createTest(req, resp);
                break;
            case "inputQuestions":
                proceedToInputQuestions(req, resp);
                break;
        }
    }

    private void proceedToInputQuestions(HttpServletRequest req, HttpServletResponse resp) {
        TestTemplate testTemplate = testService.getTestTemplateFromReq(req);

        req.getSession().setAttribute("templatePrototype", testTemplate);

        try {
            resp.sendRedirect(getServletContext().getContextPath() + "/test-template");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTest(HttpServletRequest req, HttpServletResponse resp) {
        TestTemplate testTemplate = (TestTemplate) req.getSession().getAttribute("testTemplate");

        if (testTemplate == null) {
            req.setAttribute("questionsNotLoaded", true);

            try {
                getServletContext().getRequestDispatcher("/test.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {//TODO!!! ЗДЕСЬ ЕЩЕ НУЖНО ПРОВЕРЯТЬ ДАТЫ И ПУСТЫЕ ПОЛЯ И ОТЛИЧИЯ ПОЛЕЙ ОТ ПЕРВОНАЧАЛЬНЫХ

            Test test = testService.getTestFromReq(req);
            test.setTestTemplate(testTemplate);
            TestDAOImplementation.createTest(test, (int)req.getSession().getAttribute("teacherId"));

            //TODO переадресовывать куда-нибудь
        }
    }

    private void proceedToTemplateLoad(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect(getServletContext().getContextPath() + "/template-list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
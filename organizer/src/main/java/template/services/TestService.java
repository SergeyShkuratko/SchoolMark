package template.services;

import classes.SchoolClass;
import classes.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import template.dao.ClassDAOImplementation;
import template.dao.SubjectDAOImplementation;
import template.dao.TeacherDAOImplementation;
import template.dao.TestDAOImplementation;
import template.dto.Teacher;
import template.dto.Test;
import template.dto.TestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
public class TestService {

    final private TeacherDAOImplementation teacherDAOImplementation;

    final private ClassDAOImplementation classDAOImplementation;

    final private SubjectDAOImplementation subjectDAOImplementation;

    final private TestDAOImplementation testDAOImplementation;

    @Autowired
    public TestService(TeacherDAOImplementation teacherDAOImplementation, ClassDAOImplementation classDAOImplementation, SubjectDAOImplementation subjectDAOImplementation, TestDAOImplementation testDAOImplementation) {
        this.teacherDAOImplementation = teacherDAOImplementation;
        this.classDAOImplementation = classDAOImplementation;
        this.subjectDAOImplementation = subjectDAOImplementation;
        this.testDAOImplementation = testDAOImplementation;
    }

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
        if (testTemplate == null){
            testTemplate = (TestTemplate) req.getSession().getAttribute("templatePrototype");
        }
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");

        SchoolClass schoolClass = classDAOImplementation.getClassByNumAndName(
                testTemplate.getClassNum(),
                req.getParameter("className"),
                teacher.getSchoolId());

        return schoolClass;
    }

    public Teacher getTeacherByUserId(Integer userId) {
        return teacherDAOImplementation.getTeacherByUserId(userId);
    }

    public List<Subject> getAllSubjects() {
        return subjectDAOImplementation.getAllSubjects();
    }

    public List<Integer> getClassNumbersByTeacher(Teacher teacher) {
        return classDAOImplementation.getClassNumbersByTeacher(teacher);
    }

    public List<String> getClassNamesByTeacher(Teacher teacher) {
        return classDAOImplementation.getClassNamesByTeacher(teacher);
    }

    public void createTest(Test test, Teacher teacher) {
        testDAOImplementation.createTest(test, teacher);
    }
}
package services.impl;

import classes.Student;
import classes.Teacher;
import classes.User;
import classes.UserCredentials;
import dao.StudentDAOImpl;
import dao.TeacherDAOImpl;
import dao.UserDAOImpl;
import exceptions.UserNotFoundException;
import interfaces.dao.StudentDAO;
import interfaces.dao.TeacherDAO;
import interfaces.dao.UserDAO;
import services.AuthorizationService;

import javax.servlet.http.HttpSession;

import static classes.CommonSettings.*;
import static utils.PasswordEncoder.encode;

public class AuthorizationServiceImpl implements AuthorizationService {

    private static UserDAO userDAO = new UserDAOImpl();
    private static TeacherDAO teacherDAO = new TeacherDAOImpl();
    private static StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public User auth(String login, String password) throws UserNotFoundException {
        User user = null;
        if (login != null || password != null) {
            try {
                user = userDAO.getByCredentials(new UserCredentials(login, encode(password)));
            } catch (UserNotFoundException e) {
                throw new UserNotFoundException(e.fillInStackTrace());
            }
        }
        return user;
    }

    @Override
    public String getCabinetUrl(User user) {
        switch (user.getRole().getName()) {
            case 0 :
                return TEACHER_CABINET;
            case 1 :
                return STUDENT_CABINET;
            case 2 :
                return DIRECTOR_CABINET;
            case 3 :
                return ADMIN_CABINET;
        }
        return AUTH_URL;
    }

    @Override
    public void saveUserToSession(User user, HttpSession session) {
        session.setAttribute(AUTH_ATTRIBUTE, true);
        switch (user.getRole().getName()) {
            case 0 :
                Teacher teacher = teacherDAO.getTeacher(user.getId());
                session.setAttribute(TEACHER_ATTRIBUTE, teacher);
                break;
            case 1 :
                Student student = studentDAO.getStudent(user.getId());
                session.setAttribute(STUDENT_ATTRIBUTE, student);
                break;
            case 2 :
                Teacher director = teacherDAO.getTeacher(user.getId());
                session.setAttribute(DIRECTOR_ATTRIBUTE, director);
                break;
            case 3 :
                session.setAttribute(ADMIN_ATTRIBUTE, user);
                break;
        }
    }
}

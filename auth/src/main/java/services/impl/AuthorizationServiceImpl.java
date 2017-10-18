package services.impl;

import classes.User;
import classes.UserCredentials;
import dao.UserDAOImpl;
import exceptions.UserNotFoundException;
import interfaces.dao.UserDAO;
import services.AuthorizationService;

import javax.servlet.http.HttpSession;

import static classes.CommonSettings.*;
import static utils.PasswordEncoder.encode;

public class AuthorizationServiceImpl implements AuthorizationService {

    private static UserDAO userDAO = new UserDAOImpl();

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
        session.setAttribute(AUTH_USER_ATTRIBUTE, user.getId());
        session.setAttribute(AUTH_ROLE_ATTRIBUTE, user.getRole().getId());
    }
}

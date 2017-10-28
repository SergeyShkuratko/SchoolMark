package services.impl;

import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import interfaces.dao.UserDAO;
import org.springframework.stereotype.Service;
import services.AuthorizationService;

import javax.servlet.http.HttpSession;

import static classes.CommonSettings.*;
import static utils.PasswordEncoder.encode;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private UserDAO userDAO;

    public AuthorizationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User auth(String login, String password) throws UserNotFoundException, UserDAOException {
        User user = null;
        if (login != null && password != null) {
            user = userDAO.getByCredentials(new UserCredentials(login, encode(password)));
        }
        return user;
    }

    @Override
    public String getCabinetUrl(User user) {
        switch (user.getRole()) {
            case teacher:
                return TEACHER_CABINET;
            case student:
                return STUDENT_CABINET;
            case director:
                return DIRECTOR_CABINET;
            case admin:
                return ADMIN_CABINET;
        }
        return AUTH_URL;
    }

    @Override
    public void saveUserToSession(User user, HttpSession session) {
        session.setAttribute(AUTH_USER_ATTRIBUTE, user.getUserId());
        session.setAttribute(AUTH_ROLE_ATTRIBUTE, user.getRole());
    }
}

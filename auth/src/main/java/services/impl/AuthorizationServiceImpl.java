package services.impl;

import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import dao.UserDAO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import services.AuthorizationService;

import javax.servlet.http.HttpSession;

import static classes.CommonSettings.*;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    public AuthorizationServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User auth(String login, String password) throws UserNotFoundException, UserDAOException {
        User user = null;
        if (login != null && password != null) {
            user = userDAO.getByCredentials(new UserCredentials(login, passwordEncoder.encode(password)));
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

package services;

import classes.User;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;

import javax.servlet.http.HttpSession;

public interface AuthorizationService {

    User auth(String login, String password) throws UserNotFoundException, UserDAOException;
    String getCabinetUrl(User user);
    void saveUserToSession(User user, HttpSession session);
}

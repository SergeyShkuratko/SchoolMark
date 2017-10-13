package services.impl;

import classes.User;
import classes.UserCredentials;
import dao.UserDAOImpl;
import exceptions.UserNotFoundException;
import interfaces.dao.UserDAO;
import services.AuthorizationService;

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
}

package services.impl;

import classes.User;
import classes.UserCredentials;
import dao.UserDAOImpl;
import exceptions.UserDAOException;
import interfaces.dao.UserDAO;
import services.RegistrationService;

import static utils.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {

    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public User register(String login, String password) {
        if (login == null || password == null) {
            return null;
        }
        UserCredentials credentials = new UserCredentials(login, encode(password));
        try {
            return userDAO.register(credentials);
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

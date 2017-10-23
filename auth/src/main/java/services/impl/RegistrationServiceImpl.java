package services.impl;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import dao.RoleDAOImpl;
import dao.UserDAOImpl;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;
import interfaces.dao.RoleDAO;
import interfaces.dao.UserDAO;
import org.apache.log4j.Logger;
import services.RegistrationService;

import static core.dao.utils.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {

    private static UserDAO userDAO = new UserDAOImpl();
    private static RoleDAO roleDAO = new RoleDAOImpl();
    private final static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

    @Override
    public User register(String login, String password, Role role) {
        if (login == null || password == null) {
            return null;
        }
        UserCredentials credentials = new UserCredentials(login, encode(password));
        try {
            return userDAO.register(credentials, role);
        } catch (UserDAOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Role getRoleFromUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException {
        return roleDAO.getRoleByUrl(url);
    }

}

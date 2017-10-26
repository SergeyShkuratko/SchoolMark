package services.impl;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;
import interfaces.dao.RoleDAO;
import interfaces.dao.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import services.RegistrationService;

import static utils.PasswordEncoder.encode;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

    private static UserDAO userDAO;
    private RoleDAO roleDAO;

    public RegistrationServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public User register(String login, String password, Role role) throws UserDAOException {
        if (login != null && password != null) {
            UserCredentials credentials = new UserCredentials(login, encode(password));
            try {
                return userDAO.register(credentials, role);
            } catch (UserDAOException e) {
                logger.error(e.getMessage(), e);
                throw e;
            }
        }
        throw new UserDAOException();
    }

    @Override
    public Role getRoleFromUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException {
        return roleDAO.getRoleByUrl(url);
    }

}

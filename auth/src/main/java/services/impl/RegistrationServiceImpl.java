package services.impl;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import classes.dto.TokenSavedInfo;
import dao.RegistrationTokenDAOImpl;
import dao.UserDAOImpl;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RegistrationTokenDAOException;
import exceptions.UserDAOException;
import interfaces.dao.RegistrationTokenDAO;
import interfaces.dao.UserDAO;
import org.apache.log4j.Logger;
import services.RegistrationService;

import static utils.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {

    private static UserDAO userDAO = new UserDAOImpl();
    private static RegistrationTokenDAO registrationTokenDAO = new RegistrationTokenDAOImpl();
    private static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

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
    public Role getRoleForToken(String token) throws RegistrationTokenDAOException, RegistrationTokenNotFoundException {
        return registrationTokenDAO.getRoleByUrl(token);
    }

    @Override
    public TokenSavedInfo getSavedInfoForToken(String token) throws RegistrationTokenNotFoundException, RegistrationTokenDAOException {
        return registrationTokenDAO.getSavedInfoByToken(token);
    }

}

package services.impl;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import dao.RoleDAOImpl;
import dao.UserDAOImpl;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import interfaces.dao.RoleDAO;
import interfaces.dao.UserDAO;
import org.apache.log4j.Logger;
import services.RegistrationService;

import static utils.PasswordEncoder.encode;

public class RegistrationServiceImpl implements RegistrationService {

    private static UserDAO userDAO = new UserDAOImpl();
    private static RoleDAO roleDAO = new RoleDAOImpl();
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
    public Role getRoleFromUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException {
        return roleDAO.getRoleByUrl(url);
    }

    @Override
    public String getOrCreateLinkByRoleAndSchool(Role role, int schoolId) {
        return "/register/" + "sadsadsa";
    }

    @Override
    public String getRegistrationFormHeader(Role role) {
        switch (role.getName()){
            case 1:
                return "Регистрация ученика";
            case 2:
                return "Регистрация директора";
            default:
                return "Регистрация учителя";
        }
    }

}

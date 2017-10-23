package services.impl;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import dao.RoleDAOImpl;
import dao.UserDAOImpl;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;
import interfaces.dao.RoleDAO;
import interfaces.dao.UserDAO;
import org.apache.log4j.Logger;
import services.RegistrationService;

import java.util.Random;

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
    public Role getRoleFromUrl(String url) throws RoleDAOException, RegistrationTokenNotFoundException {
        return roleDAO.getRoleByToken(url);
    }

    @Override
    public String getOrCreateLinkByRoleAndSchool(Role role, int schoolId) throws RoleDAOException {
        String token;
        try {
            token = roleDAO.getTokenByRoleAndSchool(role, schoolId);
        } catch (RoleDAOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (RegistrationTokenNotFoundException e) {
            token = generateToken();
            roleDAO.setTokenForRoleAndSchool(token, role, schoolId);
        }
        return "/register/" + token;
    }

    @Override
    public String getRegistrationFormHeader(Role role) {
        switch (role){
            case student:
                return "Регистрация ученика";
            case director:
                return "Регистрация директора";
            default:
                return "Регистрация учителя";
        }
    }

    private String generateToken() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        String token = "";
        Random random = new Random();
        int length = 5 + random.nextInt(8);
        for (int i = 0; i < length; i++) {
            token += alphabet.charAt(random.nextInt(36));
        }
        return token;
    }

}

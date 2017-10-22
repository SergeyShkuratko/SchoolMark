package services;

import classes.Role;
import classes.User;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;

public interface RegistrationService {
    User register(String login, String password, Role role) throws UserDAOException;
    Role getRoleFromUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException;
}

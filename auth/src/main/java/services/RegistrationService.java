package services;

import classes.Role;
import classes.User;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;

public interface RegistrationService {
    User register(String login, String password, Role role);
    Role getRoleFromUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException;
}

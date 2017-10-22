package services;

import classes.Role;
import classes.User;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;

public interface RegistrationService {

    User register(String login, String password, Role role) throws UserDAOException;
    String getOrCreateLinkByRoleAndSchool(Role role, int schoolId);
    String getRegistrationFormHeader(Role role);
    Role getRoleFromUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException;
}

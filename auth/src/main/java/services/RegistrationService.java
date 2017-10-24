package services;

import classes.Role;
import classes.User;
import classes.dto.TokenSavedInfo;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RegistrationTokenDAOException;
import exceptions.UserDAOException;

public interface RegistrationService {
    User register(String login, String password, Role role) throws UserDAOException;
    Role getRoleForToken(String token) throws RegistrationTokenDAOException, RegistrationTokenNotFoundException;
    TokenSavedInfo getSavedInfoForToken(String token) throws RegistrationTokenNotFoundException, RegistrationTokenDAOException;
}

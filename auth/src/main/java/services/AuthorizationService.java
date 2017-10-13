package services;

import classes.User;
import exceptions.UserNotFoundException;

public interface AuthorizationService {

    User auth(String login, String password) throws UserNotFoundException;
}

package services;

import classes.User;

public interface RegistrationService {
    User register(String login, String password);
}

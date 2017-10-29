package dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import security.CustomUser;

import java.util.Optional;

public interface UserDAO {
    User getByCredentials(UserCredentials credentials) throws UserNotFoundException, UserDAOException;

    User register(UserCredentials credentials, Role role) throws UserDAOException;

    Optional<CustomUser> getByUsername(String username);
}

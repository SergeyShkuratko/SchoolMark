package dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import security.CustomUser;

import java.util.Optional;

/**
 * Created by nkm on 11.10.2017.
 */
public interface UserDAO {
    User getByCredentials(UserCredentials credentials) throws UserNotFoundException, UserDAOException;

    User register(UserCredentials credentials, Role role) throws UserDAOException;

    Optional<CustomUser> getByUsername(String username);
}

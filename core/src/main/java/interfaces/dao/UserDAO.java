package interfaces.dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;

/**
 * Created by nkm on 11.10.2017.
 */
public interface UserDAO {
    User getByCredentials(UserCredentials credentials) throws UserNotFoundException;
    User register(UserCredentials credentials) throws UserDAOException;
    Role getRole();
    boolean update(User user) throws UserDAOException;
}

package interfaces.dao;

import classes.Role;
import classes.User;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;

/**
 * Created by nkm on 11.10.2017.
 */
public interface UserDAO {
    User getUser(String login, String passwordHash) throws UserNotFoundException;
    Role getRole();
    boolean updateUser(User user) throws UserDAOException;
}

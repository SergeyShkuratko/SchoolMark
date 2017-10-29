package interfaces.dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;

public interface UserDAO {

    User getByCredentials(UserCredentials credentials) throws UserNotFoundException, UserDAOException;
    User register(UserCredentials credentials, Role role) throws UserDAOException;
    boolean update(User user) throws UserDAOException;
    User insert(User user) throws UserDAOException;
}

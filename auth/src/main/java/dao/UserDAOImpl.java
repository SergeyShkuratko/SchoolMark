package dao;

import classes.Role;
import classes.User;
import classes.UserCredentials;
import exceptions.UserDAOException;
import exceptions.UserNotFoundException;
import interfaces.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

    @Override
    public User getByCredentials(UserCredentials credentials) throws UserNotFoundException {
        return null;
    }

    @Override
    public User register(UserCredentials credentials) throws UserDAOException {
        return null;
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public boolean update(User user) throws UserDAOException {
        return false;
    }
}

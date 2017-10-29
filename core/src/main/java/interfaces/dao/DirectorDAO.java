package interfaces.dao;

import classes.User;

public interface DirectorDAO {
    User getDirector(String login);
    boolean updateDirector(User director);
}

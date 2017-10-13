package interfaces.dao;

import classes.User;

/**
 * Created by nkm on 11.10.2017.
 */
public interface DirectorDAO {
    User getDirector(String login);
    boolean updateDirector(User director);
}

package interfaces.dao;

import classes.Director;

/**
 * Created by nkm on 11.10.2017.
 */
public interface DirectorDAO {
    Director getDirector(String login);
    boolean updateDirector(Director director);
}

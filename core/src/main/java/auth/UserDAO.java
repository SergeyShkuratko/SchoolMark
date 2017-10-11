package auth;

import classes.User;

/**
 * Created by nkm on 11.10.2017.
 */
public interface UserDAO {
    User getUser(String login);
    boolean updateUser(User user);
}

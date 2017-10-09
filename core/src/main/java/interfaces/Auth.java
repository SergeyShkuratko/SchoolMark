package interfaces;

import classes.Role;
import classes.User;

import java.util.Set;

public interface Auth {

    Set<Role> getUserRoles(User user);
}
 
package interfaces.dao;

import classes.Role;
import exceptions.RegisterUrlNotFoundException;
import exceptions.RoleDAOException;

public interface RoleDAO {

    Role getRoleByUrl(String url) throws RoleDAOException, RegisterUrlNotFoundException;
}

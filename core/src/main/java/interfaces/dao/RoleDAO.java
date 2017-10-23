package interfaces.dao;

import classes.Role;
import classes.dto.SchoolDTO;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RoleDAOException;

public interface RoleDAO {

    Role getRoleByToken(String url) throws RoleDAOException, RegistrationTokenNotFoundException;
    String getTokenByRoleAndSchool(Role role, int schoolId) throws RoleDAOException, RegistrationTokenNotFoundException;
    void setTokenForRoleAndSchool(String token, Role role, int schoolId) throws RoleDAOException;
}

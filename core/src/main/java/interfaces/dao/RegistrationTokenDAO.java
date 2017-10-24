package interfaces.dao;

import classes.Role;
import classes.dto.TokenSavedInfo;
import exceptions.RegistrationTokenNotFoundException;
import exceptions.RegistrationTokenDAOException;

public interface RegistrationTokenDAO {

    Role getRoleByUrl(String url) throws RegistrationTokenDAOException, RegistrationTokenNotFoundException;
    TokenSavedInfo getSavedInfoByToken(String token) throws RegistrationTokenDAOException, RegistrationTokenNotFoundException;
}

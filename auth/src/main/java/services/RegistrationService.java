package services;

import classes.Role;
import classes.User;

public interface RegistrationService {
    User register(String login, String password);
    String getOrCreateLinkByRoleAndSchool(Role role, int schoolId);
    String getRegistrationFormHeader(Role role);
}

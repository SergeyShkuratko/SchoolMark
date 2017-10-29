package services;

import classes.Role;

public interface RegistrationUrlService {
    String generateUrl(Role role, int schoolId, int classId);
}

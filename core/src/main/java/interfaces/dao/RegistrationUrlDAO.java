package interfaces.dao;

import classes.Role;
import classes.dto.RegistrationUrlDTO;

import java.util.List;

public interface RegistrationUrlDAO {
    String getUrl(Role role, int schoolId, int classId);
    boolean setUrl(String newUrl, String oldUrl, Role role, int schoolId, int classId);
    RegistrationUrlDTO getUrlDTO(String url);
    List<RegistrationUrlDTO> getAllUrlDtoBySchool(int schoolId);
}

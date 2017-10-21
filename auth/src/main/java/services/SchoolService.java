package services;

import classes.SchoolClass;
import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;

import java.util.List;

public interface SchoolService {

    SchoolDTO getSchoolById(int id) throws SchoolDAOException;
    List<SchoolDTO> getAllSchools() throws SchoolDAOException;
    List<SchoolDTO> getSchoolsByCityId(int cityId) throws SchoolDAOException;
    List<SchoolClass> getClassesBySchoolId(int schoolId) throws SchoolDAOException;
}

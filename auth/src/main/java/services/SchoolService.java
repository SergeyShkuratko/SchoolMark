package services;

import classes.SchoolClass;
import classes.SchoolType;
import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;

import java.util.List;

public interface SchoolService {

    SchoolDTO getSchoolById(int id) throws SchoolDAOException;

    List<SchoolDTO> getAllSchools() throws SchoolDAOException;

    List<SchoolDTO> getSchoolsByCityId(int cityId) throws SchoolDAOException;

    List<SchoolClass> getClassesBySchoolId(int schoolId) throws SchoolDAOException;
    boolean insertSchool(SchoolDTO school)throws SchoolDAOException;
    boolean updateSchool(SchoolDTO school)throws SchoolDAOException;
    List<SchoolType> getAllSchoolTypes() throws SchoolDAOException;

}

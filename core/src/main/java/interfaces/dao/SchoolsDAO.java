package interfaces.dao;

import classes.SchoolClass;
import classes.SchoolType;
import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;

import java.util.List;

public interface SchoolsDAO {

    SchoolDTO getById(int id) throws SchoolDAOException;
    List<SchoolDTO> getAllSchoolsInCity(int id) throws SchoolDAOException;
    List<SchoolDTO> getAll() throws SchoolDAOException;
    List<SchoolClass> getAllClasses(int id) throws SchoolDAOException;
    boolean insertSchool(SchoolDTO school) throws SchoolDAOException;
    boolean updateSchool(SchoolDTO school) throws SchoolDAOException;
    List<SchoolType> getAllSchoolTypes() throws SchoolDAOException;
}

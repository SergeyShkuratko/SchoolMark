package interfaces.dao;

import classes.SchoolClass;
import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;

import java.util.List;

public interface SchoolsDAO {

    SchoolDTO getById(int id) throws SchoolDAOException;
    List<SchoolDTO> getAllSchoolsInCity(int id) throws SchoolDAOException;
    List<SchoolDTO> getAll() throws SchoolDAOException;
    List<SchoolClass> getAllClasses(int id) throws SchoolDAOException;
}

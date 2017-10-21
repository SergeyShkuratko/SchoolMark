package interfaces.dao;

import classes.SchoolClass;
import classes.dto.SchoolDTO;
import exceptions.SchoolDAOException;

import java.util.List;

public interface SchoolsDAO {

    public List<SchoolDTO> getAllSchoolsInCity(int id) throws SchoolDAOException;
    public List<SchoolDTO> getAll() throws SchoolDAOException;
    public List<SchoolClass> getAllClasses(int id) throws SchoolDAOException;
}

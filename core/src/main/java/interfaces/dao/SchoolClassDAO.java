package interfaces.dao;

import classes.dto.SchoolClassDTO;
import exceptions.SchoolClassDAOException;

public interface SchoolClassDAO {

    boolean persistSchool(SchoolClassDTO schoolClass) throws SchoolClassDAOException;
    SchoolClassDTO getSchoolClassById(int id) throws SchoolClassDAOException;
}

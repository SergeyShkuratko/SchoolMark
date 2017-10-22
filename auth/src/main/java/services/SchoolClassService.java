package services;

import classes.SchoolClass;
import classes.dto.SchoolClassDTO;
import exceptions.SchoolClassDAOException;

public interface SchoolClassService {

    boolean persistSchool(int schoolId, int classNum, String letter) throws SchoolClassDAOException;
    SchoolClassDTO getSchoolClassById(int id) throws SchoolClassDAOException;

}

package services;

import classes.SchoolClass;
import exceptions.SchoolClassDAOException;

public interface SchoolClassService {

    boolean add(int schoolId, int classNum, String letter) throws SchoolClassDAOException;

}

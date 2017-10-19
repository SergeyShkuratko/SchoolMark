package interfaces.dao;

import classes.City;
import classes.School;
import classes.SchoolClass;
import exceptions.CityDAOException;
import exceptions.SchoolDAOException;

import java.util.List;

public interface SchoolDAO {

    List<School> getAllSchoolsInCity(City city) throws SchoolDAOException;
    List<School> getAll() throws SchoolDAOException;

    School getById(int id) throws SchoolDAOException;

    List<SchoolClass> getAllClasses(School school) throws SchoolDAOException;
}

package interfaces.dao;

import classes.School;
import classes.SchoolClass;

import java.util.List;

public interface SchoolDAO {

    List<School> getAllSchoolsInCity(String city, String region);
    List<School> getAll();

    List<SchoolClass> getAllClasses(School school);
}

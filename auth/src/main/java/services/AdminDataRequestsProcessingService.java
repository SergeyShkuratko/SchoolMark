package services;

import classes.School;
import classes.SchoolClass;
import classes.Teacher;

import java.util.List;

public interface SchoolService {

    List<School> getSchoolsByLocation(String city, String region);
    List<SchoolClass> getClassesBySchoolId(int schoolId);
    List<Teacher> getTeachersBySchoolId(int schoolId);
}

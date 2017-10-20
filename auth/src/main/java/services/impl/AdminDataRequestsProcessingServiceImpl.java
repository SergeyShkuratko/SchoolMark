package services.impl;

import classes.School;
import classes.SchoolClass;
import classes.Teacher;
import dao.SchoolDAOImpl;
import dao.TeacherDAOImpl;
import interfaces.dao.SchoolDAO;
import interfaces.dao.TeacherDAO;
import services.SchoolService;

import java.util.List;

public class SchoolServiceImpl implements SchoolService {

    private SchoolDAO schoolDAO = new SchoolDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();

    @Override
    public List<School> getSchoolsByLocation(String city, String region) {
        List<School> schools = schoolDAO.getAllSchoolsInCity(city, region);
        return schools;
    }

    @Override
    public List<SchoolClass> getClassesBySchoolId(int schoolId) {
        List<SchoolClass> classes = schoolDAO.getAllClasses(schoolDAO.getById(schoolId));
        return classes;
    }

    @Override
    public List<Teacher> getTeachersBySchoolId(int schoolId) {
        List<Teacher> classes = teacherDAO.getTeacherBySchool(schoolId);
        return classes;
    }
}

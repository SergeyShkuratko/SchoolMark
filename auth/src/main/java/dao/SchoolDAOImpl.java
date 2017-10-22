package dao;

import classes.Role;
import classes.School;
import classes.SchoolClass;
import classes.SchoolType;
import interfaces.dao.SchoolDAO;

import java.util.ArrayList;
import java.util.List;

public class SchoolDAOImpl implements SchoolDAO {

    @Override
    public List<School> getAllSchoolsInCity(String city, String region) {
        List<School> list = new ArrayList<>();
        list.add(new School(1, "Школа №1", "1", "1", new SchoolType(1, "1")));
        list.add(new School(2, "Школа №2", "1", "1", new SchoolType(1, "1")));
        return list;
    }

    @Override
    public List<School> getAll() {
        List<School> schools = new ArrayList<>();

        schools.add(new School(1, "Школа №1", "1", "1", new SchoolType(1, "1")));
        schools.add(new School(1, "Школа №2", "1", "1", new SchoolType(1, "1")));
        schools.add(new School(1, "Школа №3", "1", "1", new SchoolType(1, "1")));
        schools.add(new School(1, "Школа №4", "1", "1", new SchoolType(1, "1")));

        return schools;
    }

    @Override
    public List<SchoolClass> getAllClasses(School school) {
        List<SchoolClass> classes = new ArrayList<>();
        classes.add(new SchoolClass(1, 11, "11А", school));
        classes.add(new SchoolClass(2, 11, "11Б", school));
        classes.add(new SchoolClass(3, 11, "11В", school));

        return classes;
    }

    public School getSchoolById(int schoolId) {
        return new School(1, "1", "asd", "asd", new SchoolType(1, "1"));
    }
}

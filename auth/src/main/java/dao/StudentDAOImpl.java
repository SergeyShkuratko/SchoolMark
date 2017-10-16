package dao;

import classes.SchoolClass;
import classes.Student;
import classes.Test;
import interfaces.dao.StudentDAO;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student getStudent(String login) {
        return null;
    }

    @Override
    public Student getStudent(int id) {
        //TODO some logic
        return null;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public List<Student> getStudentsWithMarksByTest(Test test) {
        return null;
    }

    @Override
    public List<Student> getAllStudentsInClass(SchoolClass schoolClass) {
        return null;
    }
}

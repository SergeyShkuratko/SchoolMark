package dao;

import classes.School;
import classes.Teacher;
import classes.User;
import interfaces.dao.TeacherDAO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {

    @Override
    public Teacher getTeacher(String login) {
        throw new NotImplementedException();
    }

    @Override
    public Teacher getTeacher(int id) {
        //TODO some logic
        return null;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return false;
    }

    @Override
    public List<Teacher> getTeachersByDirector(User director) {
        return null;
    }

    @Override
    public List<Teacher> getTeacherBySchool(School school) {
        return null;
    }

    @Override
    public List<Teacher> getTeacherBySchool(int id) {
        return null;
    }
}

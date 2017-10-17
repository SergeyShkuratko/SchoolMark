package dao;

import classes.School;
import classes.Teacher;
import classes.User;
import interfaces.dao.TeacherDAO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, 1, "Иванов", "Иван", "Иванович"));
        teachers.add(new Teacher(2, 2, "Петров", "Петр", "Петрович"));
        return teachers;
    }

    @Override
    public List<Teacher> getTeacherBySchool(int id) {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, 1, "Иванов", "Иван", "Иванович"));
        teachers.add(new Teacher(2, 2, "Петров", "Петр", "Петрович"));
        return teachers;
    }
}

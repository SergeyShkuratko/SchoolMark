package interfaces.dao;

import classes.School;
import classes.Teacher;
import classes.User;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface TeacherDAO {

    Teacher getTeacher(String login);
    Teacher getTeacher(int id);
    boolean updateTeacher(Teacher teacher);


    List<Teacher> getTeachersByDirector(User director);
    List<Teacher> getTeacherBySchool(School school);
    List<Teacher> getTeacherBySchool(int id);
}

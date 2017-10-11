package auth;

import classes.Teacher;

/**
 * Created by nkm on 11.10.2017.
 */
public interface TeacherDAO {
    Teacher getTeacher(String login);
    boolean updateTeacher(Teacher teacher);
}

package director;

import classes.Director;
import classes.Teacher;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface TeacherDAO {

    List<Teacher> getTeachersByDirector(Director director);
}

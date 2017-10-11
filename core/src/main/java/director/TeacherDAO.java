package director;

import classes.Teacher;
import classes.User;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface TeacherDAO {

    List<Teacher> getTeachersByDirector(User director);
}

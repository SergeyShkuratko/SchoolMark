package director;

import classes.Teacher;
import classes.Test;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface TestDAO {
    List<Test> getTestsByTeacher(Teacher teacher); //TODO или лучше получать спиоск контрольных учителя вместе с учителем в TeacherDAO ??
}

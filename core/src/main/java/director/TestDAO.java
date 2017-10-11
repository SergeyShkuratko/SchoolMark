package director;

import classes.Teacher;
import classes.TestEvent;

import java.util.List;

/**
 * Created by nkm on 11.10.2017.
 */
public interface TestDAO {
    List<TestEvent> getTestsByTeacher(Teacher teacher); //TODO или лучше получать спиоск контрольных учителя вместе с учителем в TeacherDAO ??
}

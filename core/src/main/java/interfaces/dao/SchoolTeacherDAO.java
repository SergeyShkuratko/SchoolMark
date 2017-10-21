package interfaces.dao;

import classes.School;
import classes.Teacher;

import java.util.List;

public interface TeacherListDAO {

    List<Teacher> getTeacherBySchool(int id);
}

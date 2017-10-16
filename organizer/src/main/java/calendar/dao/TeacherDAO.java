package calendar.dao;

import calendar.dao.exceptions.TeacherDAOException;
import classes.Teacher;

public interface TeacherDAO {
    Teacher getByUserId(int id) throws TeacherDAOException;
}

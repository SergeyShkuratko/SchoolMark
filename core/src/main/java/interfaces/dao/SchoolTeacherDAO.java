package interfaces.dao;

import classes.Teacher;
import classes.dto.TeacherDTO;
import exceptions.SchoolTeacherDAOException;

import java.util.List;

public interface SchoolTeacherDAO {

    boolean addTeacher(Teacher teacher) throws SchoolTeacherDAOException;
    List<TeacherDTO> getTeacherBySchool(int id) throws SchoolTeacherDAOException;
}

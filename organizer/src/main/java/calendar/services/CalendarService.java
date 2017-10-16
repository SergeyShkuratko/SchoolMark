package calendar.services;

import calendar.dao.exceptions.TeacherDAOException;
import calendar.dao.exceptions.TestDAOException;
import calendar.dto.TestDTO;
import classes.Teacher;
import classes.Test;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    List<TestDTO> getSubjectsOfTestsByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end)throws TestDAOException;
    Teacher getByUserId (int id) throws TeacherDAOException;
}

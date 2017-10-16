package calendar.services;

import calendar.dao.TeacherDAO;
import calendar.dao.TeacherDAOPostgres;
import calendar.dao.TestDAO;
import calendar.dao.TestDAOPostgres;
import calendar.dao.exceptions.TeacherDAOException;
import calendar.dao.exceptions.TestDAOException;
import classes.Teacher;
import calendar.dto.TestDTO;

import java.time.LocalDate;
import java.util.List;

public class CalendarServiceImpl implements CalendarService {
    static TeacherDAO teacherDAO = new TeacherDAOPostgres();
    static TestDAO testDAO = new TestDAOPostgres();

    @Override
    public List<TestDTO> getSubjectsOfTestsByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end) throws TestDAOException {
        return testDAO.getTestsDTOByTeacherDuringPeriod(teacher, begin, end);
    }

    @Override
    public Teacher getByUserId(int id) throws TeacherDAOException {
        return teacherDAO.getByUserId(id);
    }
}

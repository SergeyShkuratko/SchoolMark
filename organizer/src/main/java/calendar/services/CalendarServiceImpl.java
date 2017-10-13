package calendar.services;

import classes.Teacher;
import classes.Test;

import java.time.LocalDate;
import java.util.List;

public class CalendarServiceImpl implements CalendarService{
    @Override
    public List<Test> getTestsByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end) {
        return null;
    }
}

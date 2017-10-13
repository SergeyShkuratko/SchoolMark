package calendar.services;

import classes.Teacher;
import classes.Test;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    List<Test> getTestsByTeacherDuringPeriod(Teacher teacher, LocalDate begin, LocalDate end);
}

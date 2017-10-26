package calendar.services;

import calendar.dao.exceptions.TestDAOException;
import calendar.utils.CalendarCell;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    /**Получаем заполненный календарь
     * @param userId Идентификатор пользователя(учителя)
     * @param date день, месяц которого отображается
     * @return
     */
    CalendarCell[][] getCalendarCells(int userId, LocalDate date) throws TestDAOException;
}

package calendar.services;

import calendar.utils.CalendarCell;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    /**Получаем заполненный календарь
     * @param userId Идентификатор пользователя(учителя)
     * @param begin Начало периода
     * @param end Конец периода
     * @return
     */
    List<CalendarCell> getCalendarCells(int userId, LocalDate begin, LocalDate end);
}

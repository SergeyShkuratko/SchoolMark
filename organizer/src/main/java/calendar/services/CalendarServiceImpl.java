package calendar.services;

import calendar.dao.TestDao;
import calendar.dao.PgTestDao;
import calendar.dao.exceptions.TestDAOException;
import calendar.utils.CalendarCell;
import calendar.dto.TestDto;
import org.apache.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class CalendarServiceImpl implements CalendarService {
    static Logger logger = Logger.getLogger(PgTestDao.class);

    static TestDao testDao = new PgTestDao();

    @Override
    public List<CalendarCell> getCalendarCells(int userId, LocalDate begin, LocalDate end) {
        List<CalendarCell> calendar = null;
        List<TestDto> tests = null;
        LocalDate factBegin = begin;
        if (factBegin.getDayOfWeek() != DayOfWeek.MONDAY) {
            factBegin = factBegin.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }
        LocalDate factEnd = end.with(TemporalAdjusters.lastDayOfMonth());
        if (factEnd.getDayOfWeek() != DayOfWeek.SUNDAY) {
            factEnd = factEnd.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }

        try {
            tests = testDao.getTestsByUserIdDescOrder(userId, factBegin, factEnd);
        } catch (TestDAOException e) {
            logger.error("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
            return null;
        }

        calendar = new ArrayList<>();
        LocalDate currentDate = factBegin;
        while (currentDate.compareTo(factEnd) <= 0) {
            CalendarCell cc = new CalendarCell(currentDate,
                    currentDate.compareTo(begin) >= 0 && currentDate.compareTo(end) <= 0,
                    currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY,
                    currentDate.getDayOfWeek() == DayOfWeek.SUNDAY);
            while (!tests.isEmpty() && tests.get(tests.size() - 1).getStartDate().equals(currentDate)) {
                cc.addTest(tests.remove(tests.size() - 1));
            }
            calendar.add(cc);
            currentDate = currentDate.plusDays(1);
        }
        return calendar;

    }
}
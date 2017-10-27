package calendar.services;

import calendar.dao.TestDao;
import calendar.dao.PgTestDao;
import calendar.dao.exceptions.TestDAOException;
import calendar.utils.CalendarCell;
import calendar.dto.TestDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CalendarServiceImpl implements CalendarService {

    static Logger logger = Logger.getLogger(PgTestDao.class);

    private TestDao testDao;

    @Autowired
    public CalendarServiceImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public CalendarCell[][] getCalendarCells(int userId, LocalDate date) throws TestDAOException {

        Map<LocalDate, List<TestDTO>> testsByDate;
        LocalDate begin = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = date.with(TemporalAdjusters.lastDayOfMonth());

        LocalDate firstDay = getLastMondayToDate(begin);
        LocalDate lastDay = getNextSundayToDate(end);

        try {
            testsByDate = testDao.getTestsByUserIdGroupByDate(userId, firstDay, lastDay);
        } catch (TestDAOException e) {
            logger.error("Ошибка получения тестов. " + e.getLocalizedMessage(), e);
            throw e;
        }

        CalendarCell[][] calendar = new CalendarCell[getWeeksInMonthCount(date)][7];

        int week = 0;
        int dayOfWeek = 0;

        for (LocalDate currentDate = firstDay; currentDate.compareTo(lastDay) <= 0; currentDate = currentDate.plusDays(1)) {
            CalendarCell cc = new CalendarCell(currentDate,
                    isDateBetween(currentDate, begin, end));

            if (testsByDate.containsKey(currentDate)) {
                cc.addAll(testsByDate.get(currentDate));
            }

            calendar[week][dayOfWeek] = cc;

            dayOfWeek++;
            if (dayOfWeek == 7) {
                week++;
                dayOfWeek = 0;
            }
        }
        return calendar;
    }

    private LocalDate getLastMondayToDate(LocalDate date) {
        if (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            return date.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        } else {
            return date;
        }
    }

    private LocalDate getNextSundayToDate(LocalDate date) {
        if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
            return date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        } else {
            return date;
        }
    }

    private int getWeeksInMonthCount(LocalDate date) {
        LocalDate begin = getLastMondayToDate(date.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate end = getNextSundayToDate(date.with(TemporalAdjusters.lastDayOfMonth()));
        long days = ChronoUnit.DAYS.between(begin, end) + 1;
        return (int)days/7;
    }

    private boolean isDateBetween(LocalDate current, LocalDate start, LocalDate end) {
        return current.compareTo(start) >= 0 && current.compareTo(end) <= 0;
    }
}
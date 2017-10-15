package calendar.servlets;

import calendar.utils.CalendarCell;
import classes.SchoolClass;
import classes.Subject;
import classes.Test;
import classes.TestTemplate;
import calendar.services.CalendarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static org.mockito.Mockito.*;

public class CalendarServlet extends HttpServlet {
    static CalendarService calendarService;

    static {
        Random r = new Random();
        calendarService = mock(CalendarService.class);
        LocalDate startIteration = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(5);
        LocalDate endIteration = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).plusMonths(5);
        for (LocalDate currBeginMonth = startIteration; currBeginMonth.compareTo(endIteration) <= 0; currBeginMonth = currBeginMonth.plusMonths(1)) {
            LocalDate begin = currBeginMonth.with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.MONDAY));
            LocalDate end = currBeginMonth.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SUNDAY));
            List<Test> tests = new ArrayList<>();
            tests.add(new Test(0, new TestTemplate(0, "Тема1", "", 11, new Subject(0, "Математика")), null, new SchoolClass(0, 11, "А")));
            tests.add(new Test(0, new TestTemplate(0, "Тема2", "", 11, new Subject(0, "Русский")), null, new SchoolClass(0, 11, "А")));
            tests.add(new Test(0, new TestTemplate(0, "Тема3", "", 11, new Subject(0, "География")), null, new SchoolClass(0, 11, "А")));
            tests.add(new Test(0, new TestTemplate(0, "Тема4", "", 11, new Subject(0, "История")), null, new SchoolClass(0, 11, "А")));
            tests.add(new Test(0, new TestTemplate(0, "Тема5", "", 11, new Subject(0, "Химия")), null, new SchoolClass(0, 11, "А")));
            int daysInMonth = currBeginMonth.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
            int day3 = r.nextInt(daysInMonth);
            tests.get(0).setStartDate(currBeginMonth.plusDays(day3));
            tests.get(1).setStartDate(currBeginMonth.plusDays(day3));
            tests.get(2).setStartDate(currBeginMonth.plusDays(day3));
            tests.get(3).setStartDate(currBeginMonth.plusDays(r.nextInt(daysInMonth)));
            tests.get(4).setStartDate(currBeginMonth.plusDays(r.nextInt(daysInMonth)));

            when(calendarService.getTestsByTeacherDuringPeriod(null, begin, end)).thenReturn(tests);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate beginMonth = null;
        LocalDate endMonth = null;
        LocalDate begin = null;
        LocalDate end = null;
        String beginDataString = req.getParameter("beginData");

        if (beginDataString == null)
            begin = LocalDate.now();
        else
            begin = LocalDate.parse(beginDataString);
        beginMonth = begin.with(TemporalAdjusters.firstDayOfMonth());

        String command = req.getParameter("command");
        if (command != null)
            switch (command) {
                case "timeBack":
                    beginMonth = beginMonth.minusMonths(1);
                    break;
                case "timeForward":
                    beginMonth = beginMonth.plusMonths(1);
                    break;
            }


        endMonth = beginMonth.with(TemporalAdjusters.lastDayOfMonth());
        begin = beginMonth.with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.MONDAY));
        end = beginMonth.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SUNDAY));

        List<Test> tests = new ArrayList<>(calendarService.getTestsByTeacherDuringPeriod(null, begin, end));
        List<CalendarCell> calendar = new ArrayList<>();


        tests.sort(Comparator.comparing(Test::getStartDate).reversed());
        LocalDate currentDate = begin;
        while (currentDate.compareTo(end) <= 0) {
            CalendarCell cc = new CalendarCell(currentDate,
                    currentDate.compareTo(beginMonth) >= 0 && currentDate.compareTo(endMonth) <= 0,
                    currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY,
                    currentDate.getDayOfWeek() == DayOfWeek.SUNDAY);
            while (!tests.isEmpty() && tests.get(tests.size() - 1).getStartDate().equals(currentDate)) {
                cc.addSubject(tests.get(tests.size() - 1).getTemplate().getSubject().getName());
                tests.remove(tests.size() - 1);
            }
            calendar.add(cc);
            currentDate = currentDate.plusDays(1);
        }

        req.setAttribute("calendar", calendar);
        req.setAttribute("beginData", beginMonth);
        String monthName = beginMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")).toUpperCase();
        req.setAttribute("monthName", monthName);
        req.getRequestDispatcher("/calendar.jsp").forward(req, resp);
    }
}

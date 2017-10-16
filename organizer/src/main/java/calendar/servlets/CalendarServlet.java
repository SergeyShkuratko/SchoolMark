package calendar.servlets;

import calendar.dao.TeacherDAO;
import calendar.dao.TeacherDAOPostgres;
import calendar.dao.exceptions.TeacherDAOException;
import calendar.dao.exceptions.TestDAOException;
import calendar.dto.TestDTO;
import calendar.services.CalendarServiceImpl;
import calendar.utils.CalendarCell;
import classes.*;
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
    static CalendarService calendarService = new CalendarServiceImpl();
    //TODO: ИЗМЕНИТЬ НА КОРРЕКТНЫЕ ИМА АТРИБУТОВ
    static final String TEACHER_SESSION_ATTRIBUTE_NAME = "Teacher";
    static final String ID_SESSION_ATTRIBUTE_NAME = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: ИЗМЕНИТЬ НА КОРРЕКТНОЕ ИМЯ АТРИБУТА
        req.getSession().setAttribute(ID_SESSION_ATTRIBUTE_NAME, 4);

        List<String> errors = new LinkedList<>();
        Teacher teacher = (Teacher) req.getSession().getAttribute(TEACHER_SESSION_ATTRIBUTE_NAME);
        if (teacher == null) {
            Integer id = (Integer) req.getSession().getAttribute(ID_SESSION_ATTRIBUTE_NAME);
            if (id == null) {
                errors.add("Ошибка идентификации пользователя");
            } else {
                try {
                    teacher = calendarService.getByUserId(id);
                    req.getSession().setAttribute(TEACHER_SESSION_ATTRIBUTE_NAME, teacher);
                } catch (TeacherDAOException e) {
                    errors.add(e.getLocalizedMessage() + " : " + e.toString());
                }
            }
        }
        if (teacher == null)
            errors.add("Пользователь не идентифицирован как учитель");
        else {
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
            begin = beginMonth;
            if(begin.getDayOfWeek() != DayOfWeek.MONDAY)
                begin = begin.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));

            end = beginMonth.with(TemporalAdjusters.lastDayOfMonth());
            if(end.getDayOfWeek() != DayOfWeek.SUNDAY)
                end = end.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

            List<TestDTO> tests = null;
            try {
                tests = new ArrayList<>(calendarService.getSubjectsOfTestsByTeacherDuringPeriod(teacher, begin, end));
            } catch (TestDAOException e) {

            }
            List<CalendarCell> calendar = new ArrayList<>();


            tests.sort(Comparator.comparing(TestDTO::getStartDate).reversed());
            LocalDate currentDate = begin;
            while (currentDate.compareTo(end) <= 0) {
                CalendarCell cc = new CalendarCell(currentDate,
                        currentDate.compareTo(beginMonth) >= 0 && currentDate.compareTo(endMonth) <= 0,
                        currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY,
                        currentDate.getDayOfWeek() == DayOfWeek.SUNDAY);
                while (!tests.isEmpty() && tests.get(tests.size() - 1).getStartDate().equals(currentDate)) {
                    cc.addSubject(tests.get(tests.size() - 1).getSubject());
                    tests.remove(tests.size() - 1);
                }
                calendar.add(cc);
                currentDate = currentDate.plusDays(1);
            }
            req.setAttribute("calendar", calendar);
            req.setAttribute("beginData", beginMonth);
            String monthName = beginMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")).toUpperCase();
            req.setAttribute("monthName", monthName);
        }
        if (errors.isEmpty())
            req.getRequestDispatcher("/calendar.jsp").forward(req, resp);
        else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }
}

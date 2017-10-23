package calendar.servlets;

import calendar.services.CalendarServiceImpl;
import calendar.utils.CalendarCell;
import calendar.services.CalendarService;
import classes.CommonSettings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@WebServlet("/organizer/calendar")
public class CalendarServlet extends HttpServlet {
    static CalendarService calendarService = new CalendarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(CommonSettings.AUTH_USER_ATTRIBUTE, 1);

        List<String> errors = new LinkedList<>();
        Integer userId = (Integer) req.getSession().getAttribute(CommonSettings.AUTH_USER_ATTRIBUTE);

        String dayOfMonthString = req.getParameter("dayOfMonth");
        LocalDate beginMonth;
        if (dayOfMonthString == null) {
            beginMonth = LocalDate.now().withDayOfMonth(1);
        } else {
            beginMonth = LocalDate.parse(dayOfMonthString).withDayOfMonth(1);
        }
        List<CalendarCell> calendar = calendarService.getCalendarCells(userId, beginMonth, beginMonth.with(TemporalAdjusters.lastDayOfMonth()));
        if (calendar == null)
            errors.add("Ошибка при заполнении календаря");
        req.setAttribute("calendar", calendar);
        req.setAttribute("beginData", beginMonth);
        String monthName = beginMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")).toUpperCase();
        req.setAttribute("monthName", monthName);

        if (errors.isEmpty())
            req.getRequestDispatcher("/calendar.jsp").forward(req, resp);
        else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


}

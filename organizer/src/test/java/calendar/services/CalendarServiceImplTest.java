package calendar.services;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarServiceImplTest {

    @Test
    void getCalendarCells() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CalendarServiceImpl impl = new CalendarServiceImpl();
        Class<?> calendarClass = impl.getClass();
        Method method = calendarClass.getDeclaredMethod("getWeeksInMonthCount", LocalDate.class);
        method.setAccessible(true);
        assertEquals(6, method.invoke(impl, LocalDate.parse("2017-10-25")));
        assertEquals(5, method.invoke(impl, LocalDate.parse("2017-09-25")));
    }

}
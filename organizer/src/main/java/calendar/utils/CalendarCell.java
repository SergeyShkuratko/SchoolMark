package calendar.utils;

import calendar.dto.TestDTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CalendarCell {
    private boolean isCurrentPeriod;
    private List<TestDTO> tests;
    private LocalDate date;


    public CalendarCell(LocalDate date, boolean isCurrentPeriod) {
        this.date = date;
        this.isCurrentPeriod = isCurrentPeriod;
        this.tests = new LinkedList<>();
    }

    public byte getDay() {
        return (byte) date.getDayOfMonth();
    }

    public boolean isCurrentPeriod() {
        return isCurrentPeriod;
    }

    public void setCurrentPeriod(boolean currentPeriod) {
        isCurrentPeriod = currentPeriod;
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public void addTest(TestDTO test) {
        tests.add(test);
    }

    public void addAll(Collection<TestDTO> tests) {
        this.tests.addAll(tests);
    }

    public List<TestDTO> getTests() {
        return tests;
    }

    public String getFormatDate() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDisplayName() {
        String result = "";
        if (!tests.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            if(tests.size()>0) sb.append(tests.size()).append(" к.р.");
            result = sb.toString();
        }
        return result;
    }

    public String getStyleClass() {
        String result;
        if (!tests.isEmpty()) result = "has-tests";
        else if (isWeekend() && isCurrentPeriod) result = "weekend";
        else if (isCurrentPeriod) result = "current-period";
        else result = "empty";
        return result;
    }

}

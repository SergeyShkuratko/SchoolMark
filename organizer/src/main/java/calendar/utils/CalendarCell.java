package calendar.utils;

import classes.Subject;
import classes.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalendarCell {
    private byte day;
    private boolean isCurrentMonth;
    private List<String> subjects;
    private boolean isWeekend;
    private boolean isEOW;
    private LocalDate date;


    public CalendarCell(LocalDate date, boolean isCurrentMonth, boolean isWeekend, boolean isEOW) {
        this.day = (byte)date.getDayOfMonth();
        this.date = date;
        this.isCurrentMonth = isCurrentMonth;
        this.isWeekend = isWeekend;
        this.subjects = new LinkedList<>();
        this.isEOW = isEOW;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public void setCurrentMonth(boolean currentMonth) {
        isCurrentMonth = currentMonth;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public boolean isEOW() {
        return isEOW;
    }

    public void setEOW(boolean EOW) {
        isEOW = EOW;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDisplayName() {
        String result = "";
        if (!subjects.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            if(subjects.size()>0) sb.append(subjects.get(0)).append("<br>");
            if(subjects.size()>1) sb.append("+ ").append(subjects.size()-1).append("...");
            result = sb.toString();
        }
        return result;
    }

    public String getColor() {
        String result = "lightgreen";
        if (!subjects.isEmpty()) result = "salmon";
        else if (!isCurrentMonth) result = "bisque";
        else if (isWeekend) result = "violet";
        return result;
    }

}

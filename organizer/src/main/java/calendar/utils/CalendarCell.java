package calendar.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;

public class CalendarCell {
    private byte day;
    private boolean isCurrentPeriod;
    private List<String> subjects;
    private boolean isWeekend;
    private boolean isEOW;
    private LocalDate date;


    public CalendarCell(LocalDate date, boolean isCurrentPeriod, boolean isWeekend, boolean isEOW) {
        this.day = (byte)date.getDayOfMonth();
        this.date = date;
        this.isCurrentPeriod = isCurrentPeriod;
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

    public boolean isCurrentPeriod() {
        return isCurrentPeriod;
    }

    public void setCurrentPeriod(boolean currentPeriod) {
        isCurrentPeriod = currentPeriod;
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
        if (!subjects.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            if(subjects.size()>0) sb.append(subjects.size()).append(" к.р.");
            result = sb.toString();
        }
        return result;
    }

    public String getColor() {
        String result = "lightgreen";
        if (!subjects.isEmpty()) result = "salmon";
        else if (!isCurrentPeriod) result = "bisque";
        else if (isWeekend) result = "violet";
        return result;
    }

}

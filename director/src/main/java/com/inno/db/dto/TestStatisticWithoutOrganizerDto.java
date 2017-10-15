package com.inno.db.dto;

import java.time.LocalDate;

public class TestStatisticWithoutOrganizerDto {
    private int id;
    private LocalDate date;
    private String subject;
    private String className;
    private float averageMark;

    public TestStatisticWithoutOrganizerDto(int id, LocalDate date, String subject, String className, float averageMark) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.className = className;
        this.averageMark = averageMark;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getClassName() {
        return className;
    }

    public float getAverageMark() {
        return averageMark;
    }
}

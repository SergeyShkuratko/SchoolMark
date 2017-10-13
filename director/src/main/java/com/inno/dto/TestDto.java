package com.inno.dto;

import java.time.LocalDate;

public class TestDto {
    private LocalDate date;
    private String organizer;
    private String subject;
    private String className;
    private float averageMark;

    public TestDto(LocalDate date, String organizer, String subject, String className, float averageMark) {
        this.date = date;
        this.organizer = organizer;
        this.subject = subject;
        this.className = className;
        this.averageMark = averageMark;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getOrganizer() {
        return organizer;
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

package com.inno.db.dto;

public class TestStatisticDto {
    private int id;
    private String date;
    private String organizer;
    private String subject;
    private String className;
    private float averageMark;

    public TestStatisticDto(int id, String date, String organizer, String subject, String className, float averageMark) {
        this.id = id;
        this.date = date;
        this.organizer = organizer;
        this.subject = subject;
        this.className = className;
        this.averageMark = averageMark;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
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

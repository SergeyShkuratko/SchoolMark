package classes;

import java.time.LocalDate;

public class TestHistory {
    private final int id;
    private final Test test;
    private final Teacher teacher;
    private final LocalDate dateTime;
    private final int oldStatus;
    private final int newStatus;

    public TestHistory(int id, Test test, Teacher teacher, LocalDate dateTime, int oldStatus, int newStatus) {
        this.id = id;
        this.test = test;
        this.teacher = teacher;
        this.dateTime = dateTime;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public int getId() {
        return id;
    }

    public Test getTest() {
        return test;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public int getOldStatus() {
        return oldStatus;
    }

    public int getNewStatus() {
        return newStatus;
    }
}

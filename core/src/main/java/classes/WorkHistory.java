package classes;

import java.time.LocalDate;

public class WorkHistory {
    private final int id;
    private final Work work;
    private final Student student;
    private final LocalDate dateTime;
    private final Status oldStatus;
    private final Status newStatus;

    public WorkHistory(int id, Work work, Student student, LocalDate dateTime, Status oldStatus, Status newStatus) {
        this.id = id;
        this.work = work;
        this.student = student;
        this.dateTime = dateTime;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public int getId() {
        return id;
    }

    public Work getWork() {
        return work;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public Status getOldStatus() {
        return oldStatus;
    }

    public Status getNewStatus() {
        return newStatus;
    }
}


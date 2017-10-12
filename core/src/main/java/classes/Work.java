package classes;

import java.time.LocalDate;

public class Work {
    private final int id;
    private final Test test;
    private final Student student;
    private Status status;
    private int mark;
    private String appellationComment;
    private LocalDate verificationDeadLine;

    public Work(int id, Test test, Student student) {
        this.id = id;
        this.test = test;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public Test getTest() {
        return test;
    }

    public Student getStudent() {
        return student;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getAppellationComment() {
        return appellationComment;
    }

    public void setAppellationComment(String appellationComment) {
        this.appellationComment = appellationComment;
    }

    public LocalDate getVerificationDeadLine() {
        return verificationDeadLine;
    }

    public void setVerificationDeadLine(LocalDate verificationDeadLine) {
        this.verificationDeadLine = verificationDeadLine;
    }
}

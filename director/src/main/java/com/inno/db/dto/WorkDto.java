package com.inno.db.dto;

public class WorkDto {
    private String student;
    private int mark;
    private boolean wasAppellation;

    public WorkDto(String student, int mark, boolean wasAppellation) {
        this.student = student;
        this.mark = mark;
        this.wasAppellation = wasAppellation;
    }

    public String getStudent() {
        return student;
    }

    public int getMark() {
        return mark;
    }

    public boolean isWasAppellation() {
        return wasAppellation;
    }
}

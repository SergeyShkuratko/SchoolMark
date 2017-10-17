package com.inno.db.dto;

public class WorkDto {
    private final int id;
    private String student;
    private int mark;
    private boolean wasAppeal;

    public WorkDto(int id, String student, int mark, boolean wasAppeal) {
        this.id = id;
        this.student = student;
        this.mark = mark;
        this.wasAppeal = wasAppeal;
    }

    public String getStudent() {
        return student;
    }

    public int getMark() {
        return mark;
    }

    public boolean isWasAppeal() {
        return wasAppeal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkDto workDto = (WorkDto) o;

        return id == workDto.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

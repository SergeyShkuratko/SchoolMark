package com.inno.db.dto;

public class CriterionDto {
    private final int id;
    private String criterion;

    public CriterionDto(int id, String criterion) {
        this.id = id;
        this.criterion = criterion;
    }

    public int getId() {
        return id;
    }

    public String getCriterion() {
        return criterion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriterionDto that = (CriterionDto) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

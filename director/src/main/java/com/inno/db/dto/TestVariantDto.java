package com.inno.db.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestVariantDto {
    private int id;
    private String variant;
    private Set<QuestionDto> questions = new HashSet<>();

    public TestVariantDto(int id, String variant) {
        this.id = id;
        this.variant = variant;
    }

    public int getId() {
        return id;
    }

    public String getVariant() {
        return variant;
    }

    public Set<QuestionDto> getQuestions() {
        return questions;
    }

    public boolean addAllQuestions(Collection<QuestionDto> newQuestions) {
        return questions.addAll(newQuestions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestVariantDto that = (TestVariantDto) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

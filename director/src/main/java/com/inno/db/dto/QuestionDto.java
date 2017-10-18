package com.inno.db.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QuestionDto {
    private final int id;
    private String question;
    private String answer;
    private Set<CriterionDto> criterionList = new HashSet<>();

    public QuestionDto(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.criterionList = criterionList;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Set<CriterionDto> getCriterionList() {
        return criterionList;
    }

    public boolean addAllCriterion(Collection<CriterionDto> newCriterionList) {
        return criterionList.addAll(newCriterionList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDto that = (QuestionDto) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

package com.inno.db.dto;

import java.util.Set;

public class TestAndWorksInfoDto {
    private Set<QuestionDto> questions;
    private Set<WorkDto> workList;

    public TestAndWorksInfoDto(Set<QuestionDto> questions, Set<WorkDto> workList) {
        this.questions = questions;
        this.workList = workList;
    }

    public Set<QuestionDto> getQuestions() {
        return questions;
    }

    public Set<WorkDto> getWorkList() {
        return workList;
    }
}

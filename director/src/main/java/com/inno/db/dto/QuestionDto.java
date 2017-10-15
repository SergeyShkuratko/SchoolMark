package com.inno.db.dto;

public class QuestionDto {
    private final int id;
    private String question;
    private String answer;
    private String criteria;

    public QuestionDto(int id, String question, String answer, String criteria) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.criteria = criteria;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCriteria() {
        return criteria;
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

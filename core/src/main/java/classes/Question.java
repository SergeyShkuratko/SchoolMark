package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Question {
    private final int id;
    private final TestTemplate template;
    private String question;
    private String answer;
    private List<String> criteria;

    public Question(int id, TestTemplate template, String question, String answer, Collection<String> criteria) {
        this.id = id;
        this.template = template;
        this.question = question;
        this.answer = answer;
        this.criteria = new ArrayList<>();
        this.criteria.addAll(criteria);
    }

    public int getId() {
        return id;
    }

    public TestTemplate getTemplate() {
        return template;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getCriteria() {
        return criteria;
    }

    public void addCritarion(String criterion) {
        this.criteria.add(criterion);
    }
}

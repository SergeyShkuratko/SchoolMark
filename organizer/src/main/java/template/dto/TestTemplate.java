package template.dto;

import classes.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestTemplate {
    private int id;
    private String topic;
    private String description;
    private Subject subject;
    private String difficulty;
    private int classNum;
    private LocalDate creationDate;
    private List<TestVariant> testVariants;

    public TestTemplate() {
        testVariants = new ArrayList<>();
    }

    public TestTemplate(int id, String description, Subject subject, String topic, String difficulty, int classNum, LocalDate creationDate) {
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.topic = topic;
        this.difficulty = difficulty;
        this.classNum = classNum;
        this.creationDate = creationDate;
        this.testVariants = new ArrayList<>();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public List<TestVariant> getTestVariants() {
        return testVariants;
    }

    public void setTestVariants(List<TestVariant> testVariants) {
        this.testVariants = testVariants;
    }
}

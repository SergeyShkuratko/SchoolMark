package template.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestTemplate {
    private String templateId;
    private String testTheme;
    private String subject;
    private String difficulty;
    private List<TestVariant> testVariants;

    public TestTemplate(String testTheme, String subject, String difficulty) {
        this.testTheme = testTheme;
        this.subject = subject;
        this.difficulty = difficulty;
        this.testVariants = new ArrayList<>();
    }

    public TestTemplate() {
        testVariants = new ArrayList<>();
    }

    public String getTestTheme() {
        return testTheme;
    }

    public void setTestTheme(String testTheme) {
        this.testTheme = testTheme;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<TestVariant> getTestVariants() {
        return testVariants;
    }
}

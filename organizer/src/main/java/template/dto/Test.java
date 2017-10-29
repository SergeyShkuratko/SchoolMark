package template.dto;

import classes.SchoolClass;

import java.time.LocalDate;

public class Test {
    private TestTemplate testTemplate;
    private SchoolClass schoolClass;
    private LocalDate testDate;
    private LocalDate deadlineDate;
    private String testDescription;

    public Test() {

    }

    public Test(TestTemplate testTemplate, SchoolClass schoolClass, LocalDate testDate, LocalDate deadlineDate, String testDescription) {
        this.testTemplate = testTemplate;
        this.schoolClass = schoolClass;
        this.testDate = testDate;
        this.deadlineDate = deadlineDate;
        this.testDescription = testDescription;
    }

    public TestTemplate getTestTemplate() {
        return testTemplate;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public void setTestTemplate(TestTemplate testTemplate) {
        this.testTemplate = testTemplate;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}

package template.dto;

import java.time.LocalDate;

/**
 * Created by nkm on 15.10.2017.
 */
public class Test extends TestTemplate {
    private int classNum;
    private LocalDate testDate;
    private LocalDate deadlineDate;

    public Test(String testTheme, String subject, String difficulty, int classNum, LocalDate testDate, LocalDate deadlineDate) {
        super(testTheme, subject, difficulty);
        this.classNum = classNum;
        this.testDate = testDate;
        this.deadlineDate = deadlineDate;
    }

    public Test() {

    }


    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
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

package classes;

import java.time.LocalDate;

public class Test {
    private final int id;
    private final TestTemplate template;
    private final Teacher owner;
    private final SchoolClass schoolClass;
    private Status status;
    private LocalDate startDate;
    private LocalDate verificationDate;

    public Test(int id, TestTemplate template, Teacher owner, SchoolClass schoolClass) {

        this.id = id;
        this.template = template;
        this.owner = owner;
        this.schoolClass = schoolClass;
    }

    public int getId() {
        return id;
    }

    public TestTemplate getTemplate() {
        return template;
    }

    public Teacher getOwner() {
        return owner;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDate verificationDate) {
        this.verificationDate = verificationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}

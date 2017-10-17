package calendar.dto;

import java.time.LocalDate;

public class TestDto {
    private final int id;
    private int templateId;
    private int ownerId;
    private int schoolClassId;
    private String status;
    private LocalDate startDate;
    private LocalDate verificationDate;
    private String subject;

    public TestDto(int id) {
        this.id = id;
    }

    public TestDto(int id, int templateId, int ownerId, int schoolClassId, String status, LocalDate startDate, LocalDate verificationDate) {
        this.id = id;
        this.templateId = templateId;
        this.ownerId = ownerId;
        this.schoolClassId = schoolClassId;
        this.status = status;
        this.startDate = startDate;
        this.verificationDate = verificationDate;
    }

    public int getId() {
        return id;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

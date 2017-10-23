package calendar.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestDto {
    private final int id;
    private int templateId;
    private int ownerId;
    private int schoolClassId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime verificationDate;
    private String subject;

    public TestDto(int id) {
        this.id = id;
    }

    public TestDto(int id, int templateId, int ownerId, int schoolClassId, String status, LocalDateTime startDate, LocalDateTime verificationDate) {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDateTime verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

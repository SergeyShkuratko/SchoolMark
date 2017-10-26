package student.dto;

import java.time.LocalDate;

public class DTOWork {
    private int workId;
    private int variantId;
    private LocalDate date;
    private int templId;
    private String topic;
    private String description;
    private String subject;
    private int mark;
    private String comment;
    private String status;

    public DTOWork(int workId, int variantId, LocalDate date, int templId, String topic, String description, String subject,
                   int mark, String comment, String status)
    {
        this.workId = workId;
        this.variantId = variantId;
        this.date = date;
        this.templId = templId;
        this.topic = topic;
        this.description = description;
        this.subject = subject;
        this.mark = mark;
        this.comment = comment;
        this.status = status;
    }

    @Override
    public String toString() {
        return "DTOWork{" +
                "workId=" + workId +
                ", date=" + date +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getVariantId() {
        return variantId;
    }

    public int getWorkId() {
        return workId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTemplId() {
        return templId;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public int getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }
}

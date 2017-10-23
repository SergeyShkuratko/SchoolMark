package dto;

import java.time.LocalDate;

public class DTOWork {
    private int work_id;
    private LocalDate date;
    private int templ_id;
    private String topic;
    private String description;
    private String subject;
    private int mark;
    private String comment;
    private String status;

    public DTOWork(int work_id, LocalDate date, int templ_id, String topic, String description, String subject,
                   int mark, String comment, String status)
    {
        this.work_id = work_id;
        this.date = date;
        this.templ_id = templ_id;
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
                "work_id=" + work_id +
                ", date=" + date +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getWork_id() {
        return work_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTempl_id() {
        return templ_id;
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

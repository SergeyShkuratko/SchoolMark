package inno.dto;

public class TestDTO {
   private int id;
   private String status;
   private String topic;
   private String description;
   private int school_class_id;

    public TestDTO(int id, String status, String topic, String description, int school_class_id) {
        this.id = id;
        this.status = status;
        this.topic = topic;
        this.description = description;
        this.school_class_id = school_class_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSchool_class_id() {
        return school_class_id;
    }

    public void setSchool_class_id(int school_class_id) {
        this.school_class_id = school_class_id;
    }
}

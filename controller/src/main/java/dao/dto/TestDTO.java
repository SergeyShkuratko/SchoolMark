package dao.dto;

import java.util.List;

public class TestDTO {

    private String topic;
    private String description;
    private List<Integer> workIds;

    public TestDTO(String topic, String description, List<Integer> workIds) {
        this.topic = topic;
        this.description = description;
        this.workIds = workIds;
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

    public List<Integer> getWorkIds() {
        return workIds;
    }

    public void setWorkIds(List<Integer> workIds) {
        this.workIds = workIds;
    }
}

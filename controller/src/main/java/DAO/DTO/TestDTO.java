package DAO.DTO;

import java.util.List;

public class TestDTO {

    public String topic;
    public String description;
    public List<Integer> workIds;

    public TestDTO(String topic, String description, List<Integer> workIds) {
        this.topic = topic;
        this.description = description;
        this.workIds = workIds;
    }
}

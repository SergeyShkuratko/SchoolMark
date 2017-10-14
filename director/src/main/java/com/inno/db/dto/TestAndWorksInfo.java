package com.inno.db.dto;

import java.util.List;

public class TestAndWorksInfo {
    private String description;
    private String criteria;
    private List<WorkDto> workList;

    public TestAndWorksInfo(String description, String criteria, List<WorkDto> workList) {
        this.description = description;
        this.criteria = criteria;
        this.workList = workList;
    }

    public String getDescription() {
        return description;
    }

    public String getCriteria() {
        return criteria;
    }

    public List<WorkDto> getWorkList() {
        return workList;
    }
}

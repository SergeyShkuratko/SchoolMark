package com.inno.db.dto;

import java.util.Set;

public class TestAndWorksInfoDto {
    private Set<TestVariantDto> testVariants;
    private Set<WorkDto> workList;

    public TestAndWorksInfoDto(Set<TestVariantDto> testVariants, Set<WorkDto> workList) {
        this.testVariants = testVariants;
        this.workList = workList;
    }

    public Set<TestVariantDto> getTestVariants() {
        return testVariants;
    }

    public Set<WorkDto> getWorkList() {
        return workList;
    }
}

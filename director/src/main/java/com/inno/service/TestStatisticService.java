package com.inno.service;

import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.dto.TestStatisticWithoutOrganizerDto;

import java.util.List;
import java.util.Map;

public interface TestStatisticService {
    List<TestStatisticDto> getTestsStatistic();

    Map<String, List<TestStatisticWithoutOrganizerDto>> getTestsStatisticGroupedByOwner();

    TestAndWorksInfoDto getTestAndWorksInfo(int testId);
}

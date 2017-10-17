package com.inno.service;

import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.dto.TestStatisticWithoutOrganizerDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TestStatisticService {
    List<TestStatisticDto> getTestsStatistic(LocalDate dateFrom, LocalDate dateTo);

    Map<String, List<TestStatisticWithoutOrganizerDto>> getTestsStatisticGroupedByOwner(LocalDate dateFrom, LocalDate dateTo);

    TestAndWorksInfoDto getTestAndWorksInfo(int testId);
}

package com.inno.db.dao;

import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;

import java.time.LocalDate;
import java.util.List;

public interface TestDao {
    List<TestStatisticDto> getTestsStatistic(LocalDate dateFrom, LocalDate dateTo);

    TestAndWorksInfoDto getTestAndWorksInfo(int testId);
}

package com.inno.db.dao;

import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;

import java.util.List;

public interface TestDao {
    List<TestStatisticDto> getTestsStatistic();
    TestAndWorksInfoDto getTestAndWorksInfo(int testId);
}

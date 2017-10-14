package com.inno.db.dao;

import com.inno.db.dto.TestAndWorksInfo;
import com.inno.db.dto.TestStatisticDto;

import java.util.List;

public interface TestDao {
    List<TestStatisticDto> getTestsStatistic();
    TestAndWorksInfo getTestAndWorksInfo(int testId);
}

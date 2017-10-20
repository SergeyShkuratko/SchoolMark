package com.inno.service;

import com.inno.db.dao.PgTestStatisticDao;
import com.inno.db.dao.TestStatisticDao;
import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.dto.TestStatisticWithoutOrganizerDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestStatisticServiceImpl implements TestStatisticService {
    private TestStatisticDao testStatisticDao;

    public TestStatisticServiceImpl() {
        this.testStatisticDao = new PgTestStatisticDao();
    }

    public TestStatisticServiceImpl(TestStatisticDao testStatisticDao) {
        this.testStatisticDao = testStatisticDao;
    }

    @Override
    public List<TestStatisticDto> getTestsStatistic(LocalDate dateFrom, LocalDate dateTo) {
        return testStatisticDao.getTestsStatistic(dateFrom, dateTo);
    }

    @Override
    public Map<String, List<TestStatisticWithoutOrganizerDto>> getTestsStatisticGroupedByOwner(LocalDate dateFrom,
                                                                                               LocalDate dateTo) {
        return testStatisticDao.getTestsStatistic(dateFrom, dateTo).stream()
                .collect(Collectors.groupingBy(TestStatisticDto::getOrganizer,
                        Collectors.mapping(it ->
                                new TestStatisticWithoutOrganizerDto(it.getId(), it.getDate(), it.getSubject(),
                                        it.getClassName(), it.getAverageMark()),
                                Collectors.toList())));
    }

    @Override
    public TestAndWorksInfoDto getTestAndWorksInfo(int testId) {
        return testStatisticDao.getTestAndWorksInfo(testId);
    }
}

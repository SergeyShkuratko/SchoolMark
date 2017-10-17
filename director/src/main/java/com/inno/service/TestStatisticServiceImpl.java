package com.inno.service;

import com.inno.db.dao.PgTestDao;
import com.inno.db.dao.TestDao;
import com.inno.db.dto.TestAndWorksInfoDto;
import com.inno.db.dto.TestStatisticDto;
import com.inno.db.dto.TestStatisticWithoutOrganizerDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestStatisticServiceImpl implements TestStatisticService {
    private TestDao testDao;

    public TestStatisticServiceImpl() {
        this.testDao = new PgTestDao();
    }

    public TestStatisticServiceImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public List<TestStatisticDto> getTestsStatistic(LocalDate dateFrom, LocalDate dateTo) {
        return testDao.getTestsStatistic(dateFrom, dateTo);
    }

    @Override
    public Map<String, List<TestStatisticWithoutOrganizerDto>> getTestsStatisticGroupedByOwner(LocalDate dateFrom,
                                                                                               LocalDate dateTo) {
        return testDao.getTestsStatistic(dateFrom, dateTo).stream()
                .collect(Collectors.groupingBy(TestStatisticDto::getOrganizer,
                        Collectors.mapping(it ->
                                new TestStatisticWithoutOrganizerDto(it.getId(), it.getDate(), it.getSubject(),
                                        it.getClassName(), it.getAverageMark()),
                                Collectors.toList())));
    }

    @Override
    public TestAndWorksInfoDto getTestAndWorksInfo(int testId) {
        return testDao.getTestAndWorksInfo(testId);
    }
}

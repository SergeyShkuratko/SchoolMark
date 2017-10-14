package com.inno.service;

import com.inno.db.dao.PgTestDao;
import com.inno.db.dao.TestDao;
import com.inno.db.dto.TestDto;

import java.util.List;

public class TestStatisticServiceImpl implements TestStatisticService {
    private TestDao testDao;

    public TestStatisticServiceImpl() {
        this.testDao = new PgTestDao();
    }

    public TestStatisticServiceImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public List<TestDto> findAll() {
        return testDao.findAll();
    }
}

package com.inno.service;

import com.inno.db.dto.TestDto;

import java.util.List;

public interface TestStatisticService {
    List<TestDto> findAll();
}
